package com.ebanma.cloud.mall.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.api.vo.SkuInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: why
 * @date: 2023/6/8
 * @time: 14:12
 * @description:
 */
@FeignClient(value = "mall-service" ,path="/product")
public interface SkuInfoServiceFeign {

    @GetMapping("/queryById")
    Result<SkuInfoVO> queryById(@RequestParam(value = "id") String id);
}
