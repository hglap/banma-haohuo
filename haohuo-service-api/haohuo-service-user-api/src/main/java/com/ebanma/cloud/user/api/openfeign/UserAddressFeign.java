package com.ebanma.cloud.user.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/address")
public interface UserAddressFeign {

    @PostMapping("/detail")
    Result detail(@RequestParam String userId);


}
