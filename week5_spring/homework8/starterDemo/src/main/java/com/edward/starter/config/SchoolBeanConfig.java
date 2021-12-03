package com.edward.starter.config;

import com.edward.starter.entity.Klass;
import com.edward.starter.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolBeanConfig {
    @Autowired
    private Klass klass;

    @Bean
    public School getSchool(){
        School school = new School();
        school.setClass1(klass);
        return school;
    }
}
