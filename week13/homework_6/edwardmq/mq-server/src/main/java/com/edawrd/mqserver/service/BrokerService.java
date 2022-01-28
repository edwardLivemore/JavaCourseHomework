package com.edawrd.mqserver.service;

import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgReadOffsetRequest;
import com.edward.mqcommon.req.MsgRequest;

public interface BrokerService {
    void createTopic(String topic);

    MqMessage<Order> poll(String topic) throws Exception;

    void send(MsgRequest request) throws Exception;

    void updateReadOffset(MsgReadOffsetRequest request) throws Exception;
}
