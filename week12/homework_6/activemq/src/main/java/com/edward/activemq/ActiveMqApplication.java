package com.edward.activemq;

import com.edward.activemq.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;


@SpringBootApplication
@EnableJms
public class ActiveMqApplication implements ApplicationRunner {
    @Autowired
    private Producer producer;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ActiveMqApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 发送queue
        sendQueue();

        // 发送topic
        sendTopic();

        Thread.sleep(5000);

        System.exit(0);
    }

    private void sendQueue() {
        int i = 0;
        while (i++ < 10){
            producer.sendMsg(queue, "this is No." + i + " message from " + queue);
        }
    }

    private void sendTopic() {
        int i = 0;
        while (i++ < 10){
            producer.sendMsg(topic, "this is No." + i + " message from " + topic);
        }
    }
}
