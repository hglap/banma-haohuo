package com.ebanma.cloud.order.config;


import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.mall.api.openfeign.SkuInfoServiceFeign;
import com.ebanma.cloud.order.util.RedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitSkuCount implements ApplicationRunner {

    @Autowired
    private SkuInfoServiceFeign skuInfoServiceFeign;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Result<Map<String, Long>> allSkuCount = null;
        try {
            allSkuCount = skuInfoServiceFeign.getAllSkuCount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(allSkuCount != null){
            Map<String, Long> data = allSkuCount.getData();
            data.forEach((key,value) ->{
                redisUtil.getRedisTemplate().opsForValue().set(key,value);
            });
        }
    }
}
