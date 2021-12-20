package com.edward.mysql.modules.order.controller;

import com.edward.mysql.modules.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OrderController implements ApplicationRunner {
    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1. 使用PrepareStatement批量插入100万条数据
        orderService.insertByBatch();

        // 2. 使用PrepareStatement循环100万次插入数据
        orderService.insertByLoop();

        // 3. 循环100万次插入数据
        orderService.insert();
    }
}

