package com.ebanma.cloud.seckill.service;

/**
 * @author 崔国垲
 * @version $ Id: KafkaTransMessage, v 0.1 2023/06/09 16:50 banma-0197 Exp $
 */
public interface KafkaTransMessage {

    boolean sendMessageByKafka(String message);
}
