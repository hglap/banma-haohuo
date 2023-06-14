package com.ebanma.cloud.trans.listener;

import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.service.TransAccountLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author banma-hanshuaiaho
 * @version $ Id: TransUpdateTransConsumer, v 0.1 2023/06/13 8:48 banma- Exp $
 */
@Slf4j
@Component
@RocketMQMessageListener( topic = "trans-record-topic",selectorExpression = "trans-record-tag",consumerGroup = "trans-group" )
public class TransUpdateTransConsumer implements RocketMQListener<TransAccountLog> {

    @Resource
    private TransAccountLogService transAccountLogService;

    @Override
    public void onMessage(TransAccountLog transAccountLog) {
        try {
            log.info("消息队列账务流水记录入参：{}",transAccountLog);
            transAccountLogService.record(transAccountLog);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}