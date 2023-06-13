package com.ebanma.cloud.seckill.web;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 崔国垲
 * @version $ Id: KafkaController, v 0.1 2023/06/09 16:52 banma-0197 Exp $
 */
@RequestMapping("/kafka")
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/{message}")
    public String sendMessage2(@PathVariable("message") String msg) {
        kafkaTemplate.send("seckill", msg).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败：" + ex.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                String content = String.format("发送消息成功：%s - %s - %s",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
                System.out.println(content);
            }
        });
        return "消息已发送";
    }
}
