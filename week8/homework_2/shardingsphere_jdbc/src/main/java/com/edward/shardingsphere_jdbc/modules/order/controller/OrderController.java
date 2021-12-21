package com.edward.shardingsphere_jdbc.modules.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.edward.shardingsphere_jdbc.modules.order.model.Order;
import com.edward.shardingsphere_jdbc.modules.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Code;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author eagle
 * @since 2021-12-20
 */
@Component
@Slf4j
public class OrderController implements ApplicationRunner {
    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) {
        // 清理订单表
        orderService.truncate();
        // 准备订单
        List<Order> orders = buildOrders(100);

        // 新增订单
        orderService.saveBatch(orders);

        log.info("总订单数为: {}", orderService.list().size());

        // 查询订单编号为1到20的订单
        queryOrderByCode(1, 20);

        // 修改订单编号为30到50的订单, 总价为1000
        orderService.lambdaUpdate()
                .set(Order::getTotalPrice, new BigDecimal(1000))
                .between(Order::getCode, "30", "50")
                .update();

        // 查询订单编号为30到50的订单
        queryOrderByCode(30, 50);

        // 删除订单编号为60到80的订单
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.lambda().between(Order::getCode, 60, 80);
        orderService.remove(wrapper);

        // 查询订单编号为60到80的订单
        queryOrderByCode(60, 80);

        log.info("总订单数为: {}", orderService.list().size());
    }

    private void queryOrderByCode(Integer min, Integer max) {
        List<Order> list = orderService.lambdaQuery()
                .between(Order::getCode, min, max)
                .list();
        log.info("订单编号{}到{}的订单信息:", min, max);
        list.forEach(order -> log.info("订单编号: {}, 用户id: {}, 总价: {}", order.getCode(), order.getUserId(), order.getTotalPrice()));
    }

    private List<Order> buildOrders(int num) {
        List<Order> orders = new ArrayList<>();
        for(int i = 1; i <= num; i++){
            Order order = Order.builder()
                    .code(String.valueOf(i))
                    .userId((long) i)
                    .skuIds("[" + i + "]")
                    .nums("[" + i + "]")
                    .totalPrice(new BigDecimal(i))
                    .payMethod(i)
                    .build();
            orders.add(order);
        }
        return orders;
    }
}

