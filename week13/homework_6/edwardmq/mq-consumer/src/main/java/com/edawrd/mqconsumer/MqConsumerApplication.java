package com.edawrd.mqconsumer;

import com.edawrd.mqconsumer.api.BrokerService;
import com.edward.mqcommon.entity.CommonResult;
import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgReadOffsetRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class MqConsumerApplication implements ApplicationRunner {
	@Autowired
	private BrokerService brokerService;

	public static void main(String[] args) {
		SpringApplication.run(MqConsumerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String topic = "order";

		while (true) {
			try {
				CommonResult<MqMessage<Order>> msg = brokerService.poll(topic);
				log.info("订单信息: {}", msg.getData());
			} catch (Exception e) {
				// 当订单取完时，重置订单，从头重新获取
				brokerService.updateReadOffset(new MsgReadOffsetRequest(topic, 0));
			}
			Thread.sleep(100);
		}
	}
}
