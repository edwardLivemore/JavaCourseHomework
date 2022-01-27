package com.edawrd.mqserver.service;

import com.edward.mqcommon.entity.Mq;
import com.edward.mqcommon.entity.MqMessage;

public interface BrokerService {
    void createTopic(String topic);

    Mq getMq(String topic);

    MqMessage poll(String topic) throws Exception;
}
