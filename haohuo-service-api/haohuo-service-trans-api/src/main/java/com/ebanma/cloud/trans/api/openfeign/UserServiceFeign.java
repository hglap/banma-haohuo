package com.ebanma.cloud.trans.api.openfeign;

import com.ebanma.cloud.common.dto.Result;

import com.ebanma.cloud.trans.api.dto.UserInfoSearchVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "trans-service", path = "/userInfo")
public interface UserServiceFeign {

    @GetMapping(value = "/info/getUserInfo")
    Result<UserInfoSearchVO> getUserInfo(@RequestParam("userId") String phone);

}
