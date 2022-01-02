package com.edward.banktransferbank2.modules.transfer.service;

import com.edward.banktransferbank2.modules.transfer.model.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author eagle
 * @since 2022-01-02
 */
public interface AccountService extends IService<Account> {

    void subtractBalance(Long uid, Long amount, Integer type);

    void addBalance(Long uid, Long amount, Integer type);
}
