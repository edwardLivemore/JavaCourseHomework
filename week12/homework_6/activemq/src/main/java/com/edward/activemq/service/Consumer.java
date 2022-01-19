package com.edward.activemq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {
    private final String queue = "queue";
    private final String topic = "topic";

    @JmsListener(destination = queue, containerFactory = "queueListenerFactory")
    public void receiveQueue(String msg) {
        log.info("收到{}的消息: {}", queue, msg);
    }

    @JmsListener(destination = topic, containerFactory = "topicListenerFactory")
    public void receiveTopic(String msg) {
        log.info("收到{}的消息: {}", topic, msg);
    }


}
