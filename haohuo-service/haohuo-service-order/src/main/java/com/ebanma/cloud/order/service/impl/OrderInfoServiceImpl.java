package com.ebanma.cloud.order.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.order.dao.OrderInfoMapper;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;
import com.ebanma.cloud.order.service.OrderInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    @Autowired
    private RedisTemplate redisTemplate;

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
        redisTemplate.opsForValue().set(lockKey,"20",20, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get(lockKey));
        return 0;
    }

    //@Override
    //public List<OrderInfo> listByCondition(String userId) {
    //    Condition condition = new Condition(OrderInfo.class);
    //    condition.createCriteria().andEqualTo("userId",userId);
    //    return this.findByCondition(condition);
    //}


}
