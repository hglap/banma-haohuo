package com.ebanma.cloud.order.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.order.api.dto.SkuInfoQueryDTO;
import com.ebanma.cloud.order.api.dto.countDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "order-service" ,path="/order/info")
public interface OrderInfoServiceFeign {

    @PostMapping("/querySkuSaleCount")
    Result<Map<String, countDTO>> querySkuSaleCount(@RequestBody SkuInfoQueryDTO skuInfoQueryDTO);
}
