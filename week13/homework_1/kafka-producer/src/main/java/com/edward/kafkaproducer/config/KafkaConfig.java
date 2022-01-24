package com.edward.kafkaproducer.config;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {
    @Bean
    public KafkaProducer<String, String> kafkaProducer(Properties properties) {
        return new KafkaProducer<>(properties);
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9001");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return properties;
    }
}
