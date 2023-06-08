package com.ebanma.cloud.order.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.mall.api.openfeign.SkuInfoServiceFeign;
import com.ebanma.cloud.order.dao.OrderInfoMapper;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;
import com.ebanma.cloud.order.service.OrderInfoService;
import com.ebanma.cloud.order.util.RedisUtil;
import javafx.beans.binding.StringBinding;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private DefaultRedisScript<Long> redisScript;

    @Autowired
    private RedisUtil redisUtil;


    //@Autowired
    //private RocketMQTemplate rocketMQTemplate;


    @Override
    public List<OrderInfoDTO> queryAll(OrderInfoDTO orderInfoDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotBlank(orderInfoDTO.getUserId())){
            queryWrapper.eq("user_id",orderInfoDTO.getUserId());
        }
        if(StringUtils.isNotBlank(orderInfoDTO.getMerchantId())){
            queryWrapper.eq("merchant_id",orderInfoDTO.getMerchantId());
        }
        List<OrderInfoDTO> list = orderInfoMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public OrderInfoDTO detail(Long id) {
        OrderInfo orderInfo = orderInfoMapper.selectById(id);

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtil.copyProperties(orderInfo,orderInfoDTO);
        return orderInfoDTO;
    }

    @Override
    public int update(OrderInfo orderInfo) {
       return orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public int save(OrderInfo orderInfo) {
        String lockKey = orderInfo.getSkuId();
        redisUtil.getRedisTemplate().opsForValue().set(lockKey,20+"");
        redisUtil.getRedisTemplate().opsForValue().decrement(lockKey,2);

        System.out.println(redisUtil.getRedisTemplate().opsForValue().get(lockKey));
        List<String> keyList = new ArrayList<>();
        keyList.add(lockKey);
        Object execute = redisUtil.getRedisTemplate().execute(redisScript, keyList, 2+"");


        if ("1".equals(execute.toString())) {
            // 成功后，发送消息
            //rocketMQTemplate.syncSend("hgl-order-topic"
            //        ,MessageBuilder.withPayload("测试消息").build(),3000,3);
            System.out.println("扣减库存成功");
        } else {
            // 失败后返回结果
            System.out.println("扣减库存失败");
        }
        System.out.println(redisUtil.getRedisTemplate().opsForValue().get(lockKey));
        return 0;
    }

    //@Override
    //public List<OrderInfo> listByCondition(String userId) {
    //    Condition condition = new Condition(OrderInfo.class);
    //    condition.createCriteria().andEqualTo("userId",userId);
    //    return this.findByCondition(condition);
    //}


}
