package com.edward.kafkaconsumer.service.impl;

import com.alibaba.fastjson.JSON;
import com.edward.kafkaconsumer.model.Order;
import com.edward.kafkaconsumer.service.ConsumerService;
import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private KafkaConsumer<String, String> consumer;

    @Override
    public void consumeOrder(String topic) {
        consumer.subscribe(Collections.singletonList(topic));

        try {
            while (true) { //拉取数据
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(1));

                for (ConsumerRecord o : poll) {
                    ConsumerRecord<String, String> record = (ConsumerRecord) o;
                    Order order = JSON.parseObject(record.value(), Order.class);
                    System.out.println(" order = " + order);
//                    deduplicationOrder(order);
//                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
//                            new OffsetAndMetadata(record.offset() + 1, "no matadata"));
//                    consumer.commitAsync(currentOffsets, new OffsetCommitCallback() {
//                        @Override
//                        public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
//                            if (exception != null) {
//                                exception.printStackTrace();
//                            }
//                        }
//                    });
                }
            }
        } catch (CommitFailedException e) {
            e.printStackTrace();
        } finally {
            try {
                consumer.commitSync();//currentOffsets);
            } catch (Exception e) {
                consumer.close();
            }
        }
    }
}
