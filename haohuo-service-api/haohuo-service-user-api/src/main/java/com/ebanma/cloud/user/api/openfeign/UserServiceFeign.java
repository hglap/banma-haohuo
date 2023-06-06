package com.ebanma.cloud.user.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.user.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/users")
public interface UserServiceFeign {

    @GetMapping(value = "/admin/{token}")
    Result getAdminUserByToken(@PathVariable(value = "token") String token);

    @GetMapping(value = "/mall/getDetailByToken")
    Result<UserDTO> getAppUserByToken(@RequestParam(value = "token") String token);
}
