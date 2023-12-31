package com.ebanma.cloud.order.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.mall.api.openfeign.SkuInfoServiceFeign;
import com.ebanma.cloud.mall.api.vo.SkuInfoVO;
import com.ebanma.cloud.order.dao.OrderInfoMapper;
import com.ebanma.cloud.order.feign.SkuInfoQueryDTO;
import com.ebanma.cloud.order.feign.countDTO;
import com.ebanma.cloud.order.model.AccountInfo;
import com.ebanma.cloud.order.model.DisplayOrder;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;
import com.ebanma.cloud.order.service.OrderInfoService;
import com.ebanma.cloud.order.util.RedisUtil;
import com.ebanma.cloud.trans.api.dto.TransAccountLogDTO;
import com.ebanma.cloud.trans.api.dto.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.api.dto.TransAccountLogVO;
import com.ebanma.cloud.trans.api.openfeign.TransFeign;
import com.ebanma.cloud.user.api.dto.Address;
import com.ebanma.cloud.user.api.openfeign.UserServiceFeign;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private DefaultRedisScript<Long> redisScript;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SkuInfoServiceFeign skuInfoServiceFeign;

    @Autowired
    private TransFeign transFeign;

    @Autowired
    private UserServiceFeign userServiceFeign;

    //@Autowired
    //private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;


    @Override
    public List<OrderInfoDTO> queryAll(OrderInfoDTO orderInfoDTO) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(StringUtils.isNotBlank(orderInfoDTO.getUserId())){
            queryWrapper.eq("user_id",orderInfoDTO.getUserId());
        }
        if(StringUtils.isNotBlank(orderInfoDTO.getOrderId())){
            queryWrapper.like("order_id",orderInfoDTO.getOrderId());
        }
        if(StringUtils.isNotBlank(orderInfoDTO.getConsignee())){
            queryWrapper.like("consignee",orderInfoDTO.getConsignee());
        }
        if(StringUtils.isNotBlank(orderInfoDTO.getConsigneeTel())){
            queryWrapper.like("consignee_tel",orderInfoDTO.getConsigneeTel());
        }
        if(StringUtils.isNotBlank(orderInfoDTO.getOrderStatus())){
            queryWrapper.like("order_status",orderInfoDTO.getOrderStatus());
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
        if(orderInfo == null){
            return null;
        }
        Result<SkuInfoVO> skuInfoVOResult = null;
        try {
            skuInfoVOResult = skuInfoServiceFeign.queryById(orderInfo.getSkuId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        BeanUtil.copyProperties(orderInfo,orderInfoDTO);
        if(skuInfoVOResult != null){
            orderInfoDTO.setSkuInfoVO(skuInfoVOResult.getData());
        }
        return orderInfoDTO;
    }

    @Override
    public int update(OrderInfo orderInfo) {
       return orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public Result<OrderInfo> save(OrderInfo orderInfo) {
        String skuId = orderInfo.getSkuId();
        List<String> keyList = new ArrayList<>();
        keyList.add(skuId);
        Object execute = redisUtil.getRedisTemplate().execute(redisScript, keyList, orderInfo.getSkuNum()+"");
        if ("1".equals(execute.toString())) {
            // 新增订单
            orderInfo.setOutTradeNo(UUID.randomUUID().toString());
            this.insertNewOrder(orderInfo);
            // 调用支付接口支付订单

            // 支付成功后，发送消息更新库存
            //kafkaTemplate.send("userlog","测试消息");

            //rocketMQTemplate.syncSend("hgl-order-topic"
            //        , MessageBuilder.withPayload("测试消息").build(),3000,3);
            log.info("扣减库存成功,商品id=={},商品剩余数量=={}",orderInfo.getSkuId(),redisUtil.getRedisTemplate().opsForValue().get(skuId));
            return ResultGenerator.genSuccessResult(orderInfo);
        } else {
            // 失败后返回结果
            log.info("扣减库存失败,商品id=={},商品剩余数量=={}",orderInfo.getSkuId(),redisUtil.getRedisTemplate().opsForValue().get(skuId));
            return ResultGenerator.genFailResult("库存不足！");
        }
    }

    @Async("defaultThreadPoolExecutor")
    void insertNewOrder(OrderInfo orderInfo) {
        orderInfoMapper.insert(orderInfo);
    }

    @Override
    public Result<Map<String, countDTO>> querySkuSaleCount(SkuInfoQueryDTO skuInfoQueryDTO) {

        List<String> skuIdList = skuInfoQueryDTO.getSkuIDList();
        List<String> merchantIdList = skuInfoQueryDTO.getMerchantIdList();
        if(CollectionUtils.isEmpty(skuIdList) && CollectionUtils.isEmpty(merchantIdList)){
            return ResultGenerator.genFailResult("入参为空");
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        if(CollectionUtils.isNotEmpty(skuIdList)){
            queryWrapper.in("sku_id", skuIdList);
        }
        if(CollectionUtils.isNotEmpty(merchantIdList)){
            queryWrapper.in("merchant_id", merchantIdList);
        }

        List<OrderInfo> orderInfoList = orderInfoMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(orderInfoList)){
            return ResultGenerator.genFailResult("查询失败");
        }
        Map<String, countDTO> map = new HashMap<>();
        if(CollectionUtils.isNotEmpty(skuIdList)){
            skuIdList.stream().forEach(skuId ->{
                int count = 0;
                for (OrderInfo orderInfo : orderInfoList) {
                    if (skuId.equals(orderInfo.getSkuId())){
                        count += orderInfo.getSkuNum();
                    }
                }
                countDTO countDTO = new countDTO();
                countDTO.setSkuId(skuId);
                countDTO.setSkuSaleCount(count);
                map.put(skuId, countDTO);
            });
        }else {
            merchantIdList.stream().forEach(merchantId ->{
                BigDecimal amountCount = new BigDecimal(0) ;
                int orderCount = 0;
                for (OrderInfo orderInfo : orderInfoList) {
                    if (merchantId.equals(orderInfo.getMerchantId())){
                        amountCount = amountCount.add(orderInfo.getTotalAmount());
                        orderCount ++;
                    }
                }
                countDTO countDTO = new countDTO();
                countDTO.setMerchantId(merchantId);
                countDTO.setAmountCount(amountCount);
                countDTO.setOrderCount(orderCount);
                map.put(merchantId, countDTO);
            });
        }
        return ResultGenerator.genSuccessResult(map);
    }

    @Override
    public Result<DisplayOrder> getDisplayInfo(String skuId) {
        DisplayOrder displayOrder = new DisplayOrder();
         //获取登陆人信息
        String userId = userServiceFeign.getUserIdByToken();
        Result<Address> detail = userServiceFeign.detail(userId);
        if(detail !=null){
            displayOrder.setAddress(detail.getData());
        }
        // 获取商品信息
        Result<SkuInfoVO> skuInfoVOResult = skuInfoServiceFeign.queryById(skuId);
        if (skuInfoVOResult !=null){
            displayOrder.setSkuInfo(skuInfoVOResult.getData());
        }
        // 获取红包及积分
        TransAccountLogSearchVO transAccountLogSearchVO =new TransAccountLogSearchVO();
        transAccountLogSearchVO.setBizType(0);
        Result<TransAccountLogVO> transInfo = transFeign.getTransInfo(transAccountLogSearchVO);
        if(transInfo !=null){
            List<TransAccountLogDTO> redPacketList = transInfo.getData().getLogList();
            if(CollectionUtils.isNotEmpty(redPacketList)){
                List<TransAccountLogDTO> redPackets = redPacketList.stream().filter(redPacket ->
                        redPacket.getRedPacketStatus() !=null
                                &&(redPacket.getRedPacketStatus() == 0 || redPacket.getRedPacketStatus() == 3)).collect(Collectors.toList());
                displayOrder.setRedPackets(redPackets);
                // 构建 AccountInfo 对象
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setTotalIntegral(transInfo.getData().getAmountPoints());
                displayOrder.setAccountInfo(accountInfo);
            }
        }

        return ResultGenerator.genSuccessResult(displayOrder);
    }


    //@Override
    //public List<OrderInfo> listByCondition(String userId) {
    //    Condition condition = new Condition(OrderInfo.class);
    //    condition.createCriteria().andEqualTo("userId",userId);
    //    return this.findByCondition(condition);
    //}


}
