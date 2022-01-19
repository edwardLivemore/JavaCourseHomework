package com.edward.activemq.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.*;

@Service
@Slf4j
public class Producer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMsg(Destination destination, String msg) {
        jmsTemplate.send(destination, session -> session.createTextMessage(msg));
    }
}
