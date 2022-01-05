package com.edward.banktransferbank1.modules.transfer.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboConfig {
    @Bean
    public ApplicationConfig getApplicationConfig(){
        return new ApplicationConfig();
    }
}
