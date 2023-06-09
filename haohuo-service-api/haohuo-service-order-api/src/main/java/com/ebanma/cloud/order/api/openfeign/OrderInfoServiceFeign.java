package com.ebanma.cloud.order.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.order.api.dto.SkuInfoQueryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "order-service" ,path="/order/info")
public interface OrderInfoServiceFeign {

    @PostMapping("/querySkuSaleCount")
    Result querySkuSaleCount(@RequestBody SkuInfoQueryDTO skuInfoQueryDTO);
}
