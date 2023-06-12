package com.ebanma.cloud.seckill.kafka;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.util.IdWorker;
import com.ebanma.cloud.seckill.dao.SeckillMapper;
import com.ebanma.cloud.seckill.model.dto.SeckillMessageDto;
import com.ebanma.cloud.seckill.model.po.Seckill;
import com.ebanma.cloud.seckill.service.FeignServiceGateway;
import com.ebanma.cloud.trans.api.dto.RedPacket;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: KafkaConsumer, v 0.1 2023/06/09 16:43 banma-0197 Exp $
 */
@Service
public class KafkaConsumer {


    @Resource
    private SeckillMapper seckillMapper;

    @Resource
    private FeignServiceGateway gameServiceGateway;

    private IdWorker idWorker;

//    @KafkaListener(topics = "seckill",id = "seckillMulti",groupId = "seckill_group")
    public void onMessage(List<String> records) {
        List<Seckill>  list = new ArrayList<>(records.size());
        for(String record : records){
            Seckill seckill = new Seckill();
            SeckillMessageDto seckillMessageDto =JSON.parseObject(record,SeckillMessageDto.class);
            BeanUtils.copyProperties(seckillMessageDto,seckill);
            if(seckillMessageDto.getGitName().contains("积分")){
                seckill.setPrizeType(1);
                seckill.setPrizeAmount(122);
            }
            list.add(seckill);
        }
        seckillMapper.addBantch(list);
    }
    @KafkaListener(topics = "seckill",groupId = "seckill_single_group",id = "seckillSingle")
    public void onMessage(String record) {
        SeckillMessageDto seckillMessageDto =JSON.parseObject(record,SeckillMessageDto.class);
        TransAccountLog log = new TransAccountLog();
        log.setUserId( seckillMessageDto.getUserId());
//        log.setAmount(2);
//        log.setLogType(0);
//        log.setBizType(Integer bizType);
//        log.setDescription(String "");
//        log.setSource(String source);
//        log.setCreateOn(Date createOn);
//        log.setCreateBy(String createBy);
//        log.setBizSerialNumber();
//        Result r = gameServiceGateway.updateTrans(log);
    }
}
