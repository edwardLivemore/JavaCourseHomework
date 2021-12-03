package com.edward.starter.config;

import com.edward.starter.entity.School;
import com.edward.starter.entity.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SchoolNameProperties.class)
public class MyStarterAutoConfiguration {
	@Autowired
	private School school;

	@Autowired
	private SchoolNameProperties properties;

	@Bean
	public SchoolInfo run(){
		school.setName(properties.getName());
		return new SchoolInfo(school);
	}

}