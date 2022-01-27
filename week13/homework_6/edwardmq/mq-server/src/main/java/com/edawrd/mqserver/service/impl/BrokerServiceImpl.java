package com.edawrd.mqserver.service.impl;

import com.edawrd.mqserver.service.BrokerService;
import com.edward.mqcommon.entity.Mq;
import com.edward.mqcommon.entity.MqMessage;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BrokerServiceImpl implements BrokerService {
    private static final int CAPACITY = 10000;
    private final Map<String, Mq> mqMap = new ConcurrentHashMap<>();

    @Override
    public void createTopic(String topic) {
        mqMap.putIfAbsent(topic, new Mq(CAPACITY));
    }

    @Override
    public Mq getMq(String topic) {
        return mqMap.getOrDefault(topic, null);
    }

    @Override
    public MqMessage poll(String topic) throws Exception {
        Mq mq = mqMap.getOrDefault(topic, null);
        if(mq == null) {
            throw new Exception("该topic不存在: " + topic);
        }
        return mq.poll();
    }
}
