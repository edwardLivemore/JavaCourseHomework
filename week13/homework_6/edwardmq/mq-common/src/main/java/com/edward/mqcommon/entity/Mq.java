package com.edward.mqcommon.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

@Data
@Slf4j
public class Mq<T> {

    public Mq(int capacity) {
        this.capacity = capacity;
        this.queue = new Vector<>(capacity);
        this.readOffset = 0;
        this.writeOffset = 0;
    }

    private int capacity;
    private int writeOffset;
    private int readOffset;
    private Vector<MqMessage<T>> queue;

    public MqMessage<T> poll() {
        log.info("当前readOffset为: {}", readOffset);
        return queue.get(readOffset++);
    }

    public void send(MqMessage<T> message) {
        queue.add(message);
        log.info("当前writeOffset为: {}", writeOffset);
        writeOffset++;
    }
}
