package com.edward.shardingsphere_xa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;

//@SpringBootApplication(exclude = JtaAutoConfiguration.class)
@SpringBootApplication
public class ShardingsphereXaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingsphereXaApplication.class, args);
	}

}
