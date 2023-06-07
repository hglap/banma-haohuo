package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.UserLoginEnum;
import com.ebanma.cloud.user.model.UserLogin;
import com.ebanma.cloud.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuqintao
 * @version $ Id: LoginController, v 0.1 2023/06/06 14:53 banma-0018 Exp $
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@RequestBody UserLogin userLogin) {
        if(userLogin.getType().equals(UserLoginEnum.APP_PHONE_LOGIN.getType())){
            return loginService.appCodeLogin(userLogin);
        }else if(userLogin.getType().equals(UserLoginEnum.APP_PASSWORD_LOGIN.getType())){
            return loginService.appPasswordLogin(userLogin);
        }else if(userLogin.getType().equals(UserLoginEnum.PLATFORM_PASSWORD_LOGIN.getType())){
            return loginService.platformPasswordLogin(userLogin);
        }
        return ResultGenerator.genFailResult("登陆非法");
    }

}
