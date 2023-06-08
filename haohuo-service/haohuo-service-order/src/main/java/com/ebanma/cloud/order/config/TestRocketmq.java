package com.ebanma.cloud.order.config;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author 黄贵龙
 * @version $ Id: TestRocketmq, v 0.1 2023/06/08 13:59 banma- Exp $
 */
//@Service
//@RocketMQMessageListener(consumerGroup = "test-delay",topic = "hgl-order-topic")
public class TestRocketmq implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        System.out.println("========="+s);
    }
}
