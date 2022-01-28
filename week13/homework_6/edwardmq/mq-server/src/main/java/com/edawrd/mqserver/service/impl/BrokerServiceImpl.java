package com.edawrd.mqserver.service.impl;

import com.edawrd.mqserver.service.BrokerService;
import com.edward.mqcommon.entity.Mq;
import com.edward.mqcommon.entity.MqMessage;
import com.edward.mqcommon.entity.Order;
import com.edward.mqcommon.req.MsgReadOffsetRequest;
import com.edward.mqcommon.req.MsgRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class BrokerServiceImpl implements BrokerService {
    private static final int CAPACITY = 10000;
    private final Map<String, Mq<Order>> mqMap = new ConcurrentHashMap<>();

    @Override
    public void createTopic(String topic) {
        log.info("创建topic: {}", topic);
        mqMap.putIfAbsent(topic, new Mq<>(CAPACITY));
    }

    @Override
    public MqMessage<Order> poll(String topic) throws Exception {
        Mq<Order> mq = checkTopic(topic);

        // 取出消息
        MqMessage<Order> msg = mq.poll();
        log.info("取出消息: {}", msg.getBody());
        return msg;
    }

    @Override
    public void send(MsgRequest request) throws Exception {
        Mq<Order> mq = checkTopic(request.getTopic());

        if(mq.getWriteOffset() >= CAPACITY) {
            throw new Exception("消息队列已满");
        }

        // 新增消息
        log.info("添加信息: {}", request.getMsg());
        mq.send(request.getMsg());
    }

    @Override
    public void updateReadOffset(MsgReadOffsetRequest request) throws Exception {
        Mq<Order> mq = checkTopic(request.getTopic());

        Integer readOffset = request.getReadOffset();

        // 下标越界
        if(readOffset < 0 || readOffset >= CAPACITY) {
            throw new Exception("该消息不存在");
        }

        log.info("重置readOffset为: {}", readOffset);
        mq.setReadOffset(readOffset);
    }

    private Mq<Order> checkTopic(String topic) throws Exception {
        Mq<Order> mq = mqMap.getOrDefault(topic, null);
        if(mq == null) {
            throw new Exception("该topic不存在: " + topic);
        }
        return mq;
    }
}
