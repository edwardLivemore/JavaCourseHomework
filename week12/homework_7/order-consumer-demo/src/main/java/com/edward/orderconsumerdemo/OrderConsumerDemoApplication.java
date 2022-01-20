package com.edward.orderconsumerdemo;

import com.edward.orderconsumerdemo.modules.orders.model.Orders;
import com.edward.orderconsumerdemo.modules.orders.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class OrderConsumerDemoApplication implements ApplicationRunner {
	@Autowired
	private OrdersService ordersService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(OrderConsumerDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 清理redis缓存
		Set<String> keys = redisTemplate.keys("order:*");
		if(keys.size() > 0){
			keys.forEach(key -> redisTemplate.delete(key));
		}

		// 4个消费者
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		executorService.execute(this::updateOrders);
		executorService.execute(this::updateOrders);
		executorService.execute(this::updateOrders);
		executorService.execute(this::updateOrders);

		executorService.shutdown();
		boolean terminated = executorService.isTerminated();
		while (!terminated) {
			terminated = executorService.isTerminated();
		}

		log.info("所有订单处理完成");
		System.exit(0);
	}

	private void updateOrders() {
		// 获取所有订单状态为0的订单
		List<Orders> orders = ordersService.lambdaQuery()
				.eq(Orders::getStatus, 0)
				.list();

		while (orders.size() > 0){
			for (Orders order : orders) {
				// 异常处理, 简单粗暴, 直接处理下一个订单
				try {
					Long id = order.getId();
					String key = "order:" + id;

					// redis分布式锁
					if(!redisTemplate.opsForValue().setIfAbsent(key, "1")) {
						log.info("订单号: {}已被其他消费者处理", id);
						continue;
					}

					// 打印订单信息
					log.info("正在处理订单: {}", order);
					redisTemplate.opsForValue().set(key, "1");
					ordersService.lambdaUpdate()
							.set(Orders::getStatus, 1)
							.eq(Orders::getId, id)
							.update();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 每隔100ms重新获取状态为0的订单
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			orders = ordersService.lambdaQuery()
					.eq(Orders::getStatus, 0)
					.list();
		}
	}
}
