package com.edward.shardingsphere_xa.modules.platformOrder.controller;

import com.edward.shardingsphere_xa.modules.platformOrder.model.PlatformOrder;
import com.edward.shardingsphere_xa.modules.platformOrder.service.PlatformOrderService;
import com.edward.shardingsphere_xa.modules.platformOrderItem.model.PlatformOrderItem;
import com.edward.shardingsphere_xa.modules.platformOrderItem.service.PlatformOrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2021-12-23
 */
@Component
@Slf4j
public class PlatformOrderController implements ApplicationRunner {
    @Autowired
    private PlatformOrderService platformOrderService;

    @Autowired
    private PlatformOrderItemService platformOrderItemService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 清空数据
        cleanOrders();

        // 新增订单(正常)
        platformOrderService.createOrder("1", 1L, 2,
                Arrays.asList("商品1", "商品2"),
                Arrays.asList(new BigDecimal(1), new BigDecimal(2)));

        // 查询订单
        queryOrders();

        // 新增订单(回滚)
        try {
            platformOrderService.createOrderError("2", 2L, 2,
                    Arrays.asList("商品3", "商品4"),
                    Arrays.asList(new BigDecimal(3), new BigDecimal(4)));
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询订单
        queryOrders();

        System.exit(0);
    }

    private void cleanOrders() {
        log.info("正在清理订单数据...");
        platformOrderService.truncate();
        platformOrderItemService.truncate();
    }

    private void queryOrders() {
        log.info("正在查询订单");
        List<PlatformOrder> orders = platformOrderService.list();
        orders.forEach(order -> {
            log.info("订单{}信息: ", order.getCode());
            log.info("用户id: {}", order.getUserId());
            List<PlatformOrderItem> items = platformOrderItemService.lambdaQuery()
                    .eq(PlatformOrderItem::getOrderId, order.getId())
                    .list();
            log.info("商品数量: {}", items.size());
            items.forEach(item -> {
                log.info("商品名称: {}, 价格: {}", item.getName(), item.getPrice());
            });
        });
    }
}

