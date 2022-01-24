package com.edward.kafkaproducer.service.impl;

import com.alibaba.fastjson.JSON;
import com.edward.kafkaproducer.model.Order;
import com.edward.kafkaproducer.service.OrderService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private KafkaProducer<String, String> producer;

    @Override
    public void send(String topic, Order order) {
        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, order.getId().toString(), JSON.toJSONString(order));
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        producer.close();
    }
}
