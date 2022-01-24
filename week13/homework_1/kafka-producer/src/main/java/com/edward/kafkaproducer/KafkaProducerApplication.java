package com.edward.kafkaproducer;

import com.edward.kafkaproducer.model.Order;
import com.edward.kafkaproducer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerApplication implements ApplicationRunner {
	@Autowired
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String topic = "order";
		for(int i = 1; i <= 1000; i++) {
			orderService.send(topic, new Order(i, i, i, (double) i));
		}
		orderService.close();
		System.exit(0);
	}
}
