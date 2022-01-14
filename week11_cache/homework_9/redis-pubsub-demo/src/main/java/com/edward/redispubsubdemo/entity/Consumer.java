package com.edward.redispubsubdemo.entity;

import com.alibaba.fastjson.JSON;
import com.edward.redispubsubdemo.vo.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer extends MessageListenerAdapter {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String topic = new String(message.getChannel());
        String content = new String(message.getBody());
        log.info("监听到消息, topic: {}, content: {}", topic, content);
        MessageVO messageVO = JSON.parseObject(content, MessageVO.class);

        log.info("加入到订单列表");
        redisTemplate.opsForList().rightPush("order", messageVO);
    }
}
