package com.edward.mqcommon.entity;

import lombok.Data;
import java.util.Vector;

@Data
public class Mq {

    public Mq(int capacity) {
        this.capacity = capacity;
        this.queue = new Vector<>(capacity);
        this.readOffset = 0;
        this.writeOffset = 0;
    }

    private int capacity;
    private int writeOffset;
    private int readOffset;
    private Vector<MqMessage> queue;

    public MqMessage poll() {
        return queue.get(readOffset++);
    }

    public void send(MqMessage message) {
        queue.add(message);
        writeOffset++;
    }
}
