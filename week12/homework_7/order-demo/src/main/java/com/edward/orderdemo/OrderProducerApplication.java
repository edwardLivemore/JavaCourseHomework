package com.edward.orderdemo;

import com.edward.orderdemo.modules.orders.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OrderProducerApplication implements ApplicationRunner {
	@Autowired
	private OrdersService ordersService;

	public static void main(String[] args) {
		SpringApplication.run(OrderProducerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 清理订单
		ordersService.truncate();

		// 订单总数
		int total = 100;

		// 批数
		int batchSize = 20;

		log.info("正在生成订单...");
		int current = 0;
		while(current < total) {
			// 每隔50ms生成20个订单
			ordersService.addOrders(20);
			current += batchSize;
			Thread.sleep(50);
		}
		log.info("生成订单完成");

		System.exit(0);
	}
}
