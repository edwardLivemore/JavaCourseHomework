package com.edward.redispubsubdemo.entity;

import com.alibaba.fastjson.JSON;
import com.edward.redispubsubdemo.vo.MessageVO;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Producer {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(String topic, MessageVO messageVO) {
        log.info("正在发布订单消息, topic: {}, message: {}", topic, messageVO);
        redisTemplate.getConnectionFactory().getConnection()
                .publish(topic.getBytes(CharsetUtil.UTF_8), JSON.toJSONBytes(messageVO));
    }
}
