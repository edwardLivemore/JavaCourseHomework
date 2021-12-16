package com.edward.multids_v1.config;

import com.edward.multids_v1.dataSource.DynamicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DynamicDataSourceConfig {
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDS, DataSource slave1DS){
        return new DynamicDataSource(masterDS, slave1DS);
    }

}
