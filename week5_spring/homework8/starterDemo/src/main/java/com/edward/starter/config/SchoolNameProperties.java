package com.edward.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "school.name")
public class SchoolNameProperties {
    private String name = "edward";
}
