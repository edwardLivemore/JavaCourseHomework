package com.edward.mystarter.config;

import com.edward.mystarter.entity.Klass;
import com.edward.mystarter.entity.School;
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
