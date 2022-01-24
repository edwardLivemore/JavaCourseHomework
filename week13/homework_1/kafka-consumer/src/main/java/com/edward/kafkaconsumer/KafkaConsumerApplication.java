package com.edward.kafkaconsumer;

import com.edward.kafkaconsumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerApplication implements ApplicationRunner {
	@Autowired
	private ConsumerService consumerService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String topic = "order";
		consumerService.consumeOrder(topic);
	}
}
