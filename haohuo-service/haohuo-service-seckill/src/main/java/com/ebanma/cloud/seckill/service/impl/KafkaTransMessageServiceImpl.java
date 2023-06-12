package com.ebanma.cloud.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.seckill.model.dto.SeckillMessageDto;
import com.ebanma.cloud.seckill.service.KafkaTransMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author 崔国垲
 * @version $ Id: KafkaTransMessage, v 0.1 2023/06/12 11:19 banma-0197 Exp $
 */
@Service
public class KafkaTransMessageServiceImpl implements KafkaTransMessage {

    private Logger log = LoggerFactory.getLogger(KafkaTransMessageServiceImpl.class);

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean sendMessageByKafka(String message) {
        kafkaTemplate.send("seckill",message).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("发送消息失败：{}" , ex.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, String> result) {

            }
        });
        return true;
    }
}
