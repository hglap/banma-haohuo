package com.ebanma.cloud.order.config;

import org.apache.rocketmq.spring.core.RocketMQListener;


//@Service
//@RocketMQMessageListener(consumerGroup = "test-delay",topic = "hgl-order-topic")
public class RocketmqTest implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("========="+s);
    }
}
