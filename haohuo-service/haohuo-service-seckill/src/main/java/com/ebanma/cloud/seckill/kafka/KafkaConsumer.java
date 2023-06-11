package com.ebanma.cloud.seckill.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaUtils;
import org.springframework.stereotype.Component;

/**
 * @author 崔国垲
 * @version $ Id: KafkaConsumer, v 0.1 2023/06/09 16:43 banma-0197 Exp $
 */
@Component
public class KafkaConsumer {
    // 消费监听
    @KafkaListener(topics = "seckill")
    public void onMessage(ConsumerRecord<?, ?> record) {
        //获取消费者组
        String groupId= KafkaUtils.getConsumerGroupId();
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" +groupId+"-"+ record.topic() + "-" + record.partition() + "-" + record.value());
    }
}
