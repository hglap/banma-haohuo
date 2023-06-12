package com.ebanma.cloud.user.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.user.api.dto.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/address")
public interface UserAddressFeign {

    @PostMapping("/detail")
    Result<Address> detail(@RequestParam String userId);


}
