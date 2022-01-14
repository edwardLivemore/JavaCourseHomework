package com.edward.redispubsubdemo;

import com.edward.redispubsubdemo.entity.Producer;
import com.edward.redispubsubdemo.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
@Slf4j
public class RedisPubsubDemoApplication implements ApplicationRunner {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private Producer producer;

	private final String KEY = "order";

	public static void main(String[] args) {
		SpringApplication.run(RedisPubsubDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 清理订单列表
		cleanOrderList();

		// 获取订单列表信息
		getOrderList();

		List<MessageVO> orderList = new ArrayList<>();
		// 创建订单
		for(int i = 1; i <= 10; i++){
			orderList.add(new MessageVO("order" + i, (double) (100 + i), i,
					LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
		}

		// 发送消息
		orderList.forEach(order -> producer.sendMessage("order", order));

		Thread.sleep(5000);

		// 获取订单列表信息
		getOrderList();

		System.exit(0);
	}

	private void cleanOrderList() {
		redisTemplate.delete(KEY);
	}

	private void getOrderList() {
		Long num = redisTemplate.opsForList().size(KEY);
		log.info("正在获取订单列表, 订单数为: {}", num);
		for(int i = 0; i < num; i++){
			MessageVO messageVO = (MessageVO) redisTemplate.opsForList().index(KEY, i);
			log.info("订单{}信息: {}", i + 1, messageVO.toString());
		}
	}

}
