package com.edward.mystarter.config;

import com.edward.mystarter.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentBeanConfig {
    @Bean("good")
    public Student getGoodStudent(){
        return new Student(1, "good");
    }

    @Bean("bad")
    public Student getBadStudent(){
        return new Student(2, "bad");
    }
}
