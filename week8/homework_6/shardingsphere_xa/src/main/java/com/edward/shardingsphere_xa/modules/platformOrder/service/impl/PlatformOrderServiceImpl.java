package com.edward.shardingsphere_xa.modules.platformOrder.service.impl;

import com.edward.shardingsphere_xa.modules.platformOrder.model.PlatformOrder;
import com.edward.shardingsphere_xa.modules.platformOrder.mapper.PlatformOrderMapper;
import com.edward.shardingsphere_xa.modules.platformOrder.service.PlatformOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edward.shardingsphere_xa.modules.platformOrderItem.model.PlatformOrderItem;
import com.edward.shardingsphere_xa.modules.platformOrderItem.service.PlatformOrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
@Service
@Slf4j
public class PlatformOrderServiceImpl extends ServiceImpl<PlatformOrderMapper, PlatformOrder> implements PlatformOrderService {
    @Autowired
    private PlatformOrderItemService platformOrderItemService;

    @Override
    public void truncate() {
        getBaseMapper().truncate();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void createOrder(String code, long userId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices) {
        log.info("正在生成新订单...");
        // 创建订单
        PlatformOrder order = PlatformOrder.builder().code(code).userId(userId).build();
        save(order);

        // 创建订单商品
        platformOrderItemService.createOrderItems(order.getId(), itemNum, itemNames, itemPrices);
        log.info("订单生成成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @ShardingTransactionType(TransactionType.XA)
    public void createOrderError(String code, long userId, int itemNum, List<String> itemNames, List<BigDecimal> itemPrices) {
        log.info("正在生成新订单...");
        // 创建订单
        PlatformOrder order = PlatformOrder.builder().code(code).userId(userId).build();
        save(order);

        // 创建订单商品
        // 故意设置商品数为3, 导致数组下标越界
        itemNum = 3;
        platformOrderItemService.createOrderItems(order.getId(), itemNum, itemNames, itemPrices);
        log.info("订单生成成功");
    }
}
