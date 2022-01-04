package com.edward.banktransferbank1.modules.transfer.service.impl;

import com.edward.banktransferapi.common.Bank2Service;
import com.edward.banktransferbank1.modules.transfer.model.*;
import com.edward.banktransferbank1.modules.transfer.mapper.AccountMapper;
import com.edward.banktransferbank1.modules.transfer.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.Hmily;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.core.concurrent.threadlocal.HmilyTransactionContextLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2022-01-02
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService{
    @Autowired
    private FrozenService frozenService;

    @Autowired
    private TryLogService tryLogService;

    @Autowired
    private ConfirmLogService confirmLogService;

    @Autowired
    private CancelLogService cancelLogService;

    @DubboReference(version = "1.0.0")
    Bank2Service bank2Service;

    @Override
    @Hmily(confirmMethod = "confirm", cancelMethod = "cancel")
    public void subtractBalance(Long uid, Long amount, Integer type) {
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        // try幂等校验
        List<TryLog> tryLogs = getTryLogs(transId);

        if(tryLogs.size() > 0){
            log.info("bank1 中已存在事务{}", transId);
            return;
        }

        // try悬挂处理
        List<ConfirmLog> confirmLogs = getConfirmLogs(transId);

        List<CancelLog> cancelLogs = getCancelLogs(transId);

        if(confirmLogs.size() > 0 || cancelLogs.size() > 0){
            log.info("bank1 中事务{}已confirm或cancel", transId);
        }

        // 判断余额是否足够
        Account account = lambdaQuery().eq(Account::getUserId, uid).eq(Account::getType, type).one();
        if(account.getBalance() - amount <= 0){
            log.info("余额不足");
            return;
        }

        // 扣款
        try {
            lambdaUpdate()
                    .set(Account::getBalance, account.getBalance() - amount)
                    .eq(Account::getId, account.getId())
                    .update();
        }catch (Exception e){
            throw new HmilyRuntimeException("账户A扣款异常");
        }

        // 增加try日志
        TryLog tryLog = new TryLog();
        tryLog.setTxId(transId);
        tryLogService.save(tryLog);

        // 调用bank2发起转账
        try {
            bank2Service.transfer(2L, amount, type);
        }catch (Exception e){
            throw new HmilyRuntimeException("转账发生异常");
        }

    }

    @Override
    public void addBalance(Long uid, Long amount, Integer type) {
        Account account = lambdaQuery()
                .eq(Account::getUserId, uid)
                .eq(Account::getType, type)
                .one();

        lambdaUpdate()
                .set(Account::getBalance, account.getBalance() + amount)
                .eq(Account::getId, account.getId())
                .update();
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Long uid, Long amount, Integer type){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        List<ConfirmLog> confirmLogs = getConfirmLogs(transId);

        // 幂等校验, 如果confirm操作未执行，才能去除冻结金额，否则什么也不做。
        if(confirmLogs.size() == 0){
            //只有try操作完成之后，且cancel操作未执行的情况下，才允许执行confirm
            List<TryLog> tryLogs = getTryLogs(transId);
            List<CancelLog> cancelLogs = getCancelLogs(transId);
            if(tryLogs.size() > 0 && cancelLogs.size() == 0){
                // 解冻
                unfreezeAccount(uid, amount, type);

                // 添加confirm日志
                ConfirmLog confirmLog = new ConfirmLog();
                confirmLog.setTxId(transId);
                confirmLogService.save(confirmLog);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Long uid, Long amount, Integer type){
        String transId = HmilyTransactionContextLocal.getInstance().get().getTransId();
        List<CancelLog> cancelLogs = getCancelLogs(transId);
        //幂等校验 只有当cancel操作未执行的情况下，才执行cancel，否则什么也不做。
        if(cancelLogs.size() == 0){
            //空回滚操作，如果try操作未执行，那么cancel什么也不做，当且仅当try执行之后，才能执行cancel
            List<TryLog> tryLogs = getTryLogs(transId);
            if(tryLogs.size() > 0){
                //cancel操作，需要判断confirm是否执行了
                //如果此时confirm还未执行，那么需要将冻结金额清除
                List<ConfirmLog> confirmLogs = getConfirmLogs(transId);
                if(confirmLogs.size() == 0){
                    // 解冻
                    unfreezeAccount(uid, amount, type);
                }
                // 返回账户金额
                addBalance(uid, amount, type);
                // 增加cancel日志
                CancelLog cancelLog = new CancelLog();
                cancelLog.setTxId(transId);
                cancelLogService.save(cancelLog);
            }
        }
        return true;
    }

    private void unfreezeAccount(Long uid, Long amount, Integer type) {
        Frozen frozen = frozenService.lambdaQuery()
                .eq(Frozen::getUserId, uid)
                .eq(Frozen::getAmount, amount)
                .eq(Frozen::getType, type)
                .one();
        if(frozen != null){
            frozenService.getBaseMapper().deleteById(frozen.getId());
        }
    }

    private List<TryLog> getTryLogs(String txId){
        return tryLogService.lambdaQuery().eq(TryLog::getTxId, txId).list();
    }

    private List<ConfirmLog> getConfirmLogs(String txId){
        return confirmLogService.lambdaQuery().eq(ConfirmLog::getTxId, txId).list();
    }

    private List<CancelLog> getCancelLogs(String txId){
        return cancelLogService.lambdaQuery().eq(CancelLog::getTxId,txId).list();
    }
}
