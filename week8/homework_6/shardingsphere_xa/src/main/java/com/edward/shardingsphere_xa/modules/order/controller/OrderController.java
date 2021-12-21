package com.edward.shardingsphere_xa.modules.order.controller;

import com.edward.shardingsphere_xa.modules.order.model.Order;
import com.edward.shardingsphere_xa.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2021-12-21
 */
@Component
@Slf4j
public class OrderController implements ApplicationRunner {
    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 准备数据
        List<Order> orders = buildOrders(3);

        // 清空数据
        orderService.truncate();

        // 新增订单(正常)
        orderService.addOrders(orders);

        // 查询订单记录
        queryOrders();

        // 新增订单(异常回滚)
        try {
            orderService.addOrdersError(orders);
        }catch (Exception e){
            e.printStackTrace();
        }

        // 查询订单记录
        queryOrders();

        System.exit(0);
    }

    private void queryOrders() {
        List<Order> list = orderService.list();
        log.info("订单数量为: {}", list.size());
    }

    private List<Order> buildOrders(int num) {
        List<Order> orders = new ArrayList<>();
        for (long i = 1; i <= num; i++){
            Order order = Order.builder()
                    .code(String.valueOf(i))
                    .userId(i)
                    .skuIds("[" + i + "]")
                    .nums("[" + i + "]")
                    .totalPrice(new BigDecimal(i))
                    .payMethod(1)
                    .build();
            orders.add(order);
        }
        return orders;
    }
}

