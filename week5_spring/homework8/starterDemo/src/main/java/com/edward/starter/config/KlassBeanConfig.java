package com.edward.starter.config;

import com.edward.starter.entity.Klass;
import com.edward.starter.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
public class KlassBeanConfig {
    @Resource(name = "good")
    private Student goodStudent;

    @Resource(name = "bad")
    private Student badStudent;

    @Bean
    public Klass getKlass(){
        Klass klass = new Klass();
        klass.setStudents(Arrays.asList(goodStudent, badStudent));
        return klass;
    }
}
