package com.edawrd.mqserver;

import com.edward.mqcommon.entity.MqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MqServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MqServerApplication.class, args);
	}

}
