package com.edward.kafkaproducer.service;

import com.edward.kafkaproducer.model.Order;

public interface OrderService {
    void send(String topic, Order order);

    void close();
}
