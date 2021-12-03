package com.edward.starter;

import com.edward.starter.entity.SchoolInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // 测试自动配置
    @Autowired
    private SchoolInfo schoolInfo;

    @Bean
    public void start(){
        schoolInfo.getSchool().ding();
    }
}

