package com.ebanma.cloud.mall.init;


import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.mall.api.openfeign.SkuInfoServiceFeign;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.ebanma.cloud.mall.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitSkuCount implements ApplicationRunner {

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Map<String, Long> allSkuCount = null;
        try {
            allSkuCount = skuInfoService.getAllSkuCount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(allSkuCount != null){
            allSkuCount.forEach((key,value) ->{
                redisUtil.getRedisTemplate().opsForValue().set(key,value+"");
            });
        }
    }
}
