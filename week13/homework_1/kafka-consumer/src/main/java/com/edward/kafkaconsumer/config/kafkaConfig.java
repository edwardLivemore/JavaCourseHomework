package com.edward.kafkaconsumer.config;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class kafkaConfig {
    @Bean
    public KafkaConsumer<String, String> kafkaConsumer(Properties properties) {
        return new KafkaConsumer<>(properties);
    }

    @Bean
    public Properties properties() {
        Properties properties = new Properties();
        properties.put("group.id", "edward");
        properties.put("bootstrap.servers", "localhost:9002");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}
