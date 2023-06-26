package com.ebanma.cloud.order.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTest {

    @KafkaListener(topics = {"userlog"})
    public void onMessage(ConsumerRecord record){
        System.out.println(record.topic()+"====="+record.value());
    }
}
