package com.ebanma.cloud.user.web;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@RocketMQMessageListener(topic = "prod-topic", consumerGroup = "group1")
public class ProdMsgConsumer implements RocketMQListener<String> {

    @Resource
    private ProdLifetimeService prodLifeTimeService;

    @Override
    public void onMessage(String msg) {
        // 转json拿到数据
        JSONObject jsonObject = JSONUtil.parseObj(msg);
        //传的时候传什么类型，就用什么类型取
        String userId = jsonObject.getStr("principalId");
        String principalType = jsonObject.getStr("principalType");
        String productCode = jsonObject.getStr("productCode");
        Long amount = jsonObject.getLong("amount");
        log.info("收到order-topic消息：principalId={}, principalType={}, productCode={}", userId, principalType, productCode, amount);
        prodLifeTimeService.handleMessage(userId, principalType, productCode, amount);
    }
}