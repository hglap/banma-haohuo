package com.ebanma.cloud.seckill.kafka;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.common.util.IdWorker;
import com.ebanma.cloud.seckill.dao.SeckillMapper;
import com.ebanma.cloud.seckill.model.dto.SeckillMessageDto;
import com.ebanma.cloud.seckill.model.po.Seckill;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: KafkaConsumer, v 0.1 2023/06/09 16:43 banma-0197 Exp $
 */
@Service
public class KafkaConsumer {
    // 消费监听
//    @KafkaListener(topics = "seckill")
//    public void onMessage(ConsumerRecord<String, String> record) {
//        //获取消费者组
//        String groupId= KafkaUtils.getConsumerGroupId();
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("简单消费：" +groupId+"-"+ record.topic() + "-" + record.partition() + "-" + record.value().toString());
//    }

    @Resource
    private SeckillMapper seckillMapper;

    private IdWorker idWorker;

    @KafkaListener(topics = "seckill")
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
//        //获取消费者组
//        String groupId= KafkaUtils.getConsumerGroupId();
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("简单消费：" +groupId+"-"+ record.topic() + "-" + record.partition() + "-" + record.value().toString());
    }
}
