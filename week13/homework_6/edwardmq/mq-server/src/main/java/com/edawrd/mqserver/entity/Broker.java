package com.edawrd.mqserver.entity;

import com.edward.mqcommon.entity.Mq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    public static final int CAPACITY = 10000;

    private final Map<String, Mq> mqMap = new ConcurrentHashMap<>();

    public void createTopic(String topic) {
        mqMap.putIfAbsent(topic, new Mq(CAPACITY));
    }

    public Mq findMq(String topic) {
        return mqMap.get(topic);
    }

}
