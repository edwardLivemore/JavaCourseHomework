package com.edward.mqproducer;

import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgRequest;
import com.edward.mqproducer.api.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class MqProducerApplication implements ApplicationRunner {
	@Autowired
	private BrokerService brokerService;

	public static void main(String[] args) {
		SpringApplication.run(MqProducerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String topic = "order";

		// 创建topic
		brokerService.createTopic(topic);

		Random random = new Random();
		// 发送消息
		for(int i = 1; i <= 100; i++) {
			Order order = new Order();
			order.setId(i);
			order.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
			order.setProductId(UUID.randomUUID().toString().replaceAll("-", ""));
			order.setPrice(random.nextInt(100));
			order.setOrderTime(LocalDateTime.now());
			brokerService.send(new MsgRequest(topic, new MqMessage<>(null, order)));
		}
	}
}
