package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.UserLoginEnum;
import com.ebanma.cloud.user.model.Address;
import com.ebanma.cloud.user.model.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuqintao
 * @version $ Id: LoginController, v 0.1 2023/06/06 14:53 banma-0018 Exp $
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/login")
    public Result login(UserLogin userLogin) {
        if(userLogin.getType().equals(UserLoginEnum.APP_PHONE_LOGIN.getType())){

        }else if(userLogin.getType().equals(UserLoginEnum.APP_PASSWORD_LOGIN.getType())){

        }else if(userLogin.getType().equals(UserLoginEnum.PLATFORM_PASSWORD_LOGIN.getType())){

        }
        return ResultGenerator.genSuccessResult();
    }

}
