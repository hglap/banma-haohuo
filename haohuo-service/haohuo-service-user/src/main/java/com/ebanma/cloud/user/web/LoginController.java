package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.UserLoginEnum;
import com.ebanma.cloud.user.common.BaseContextHandler;
import com.ebanma.cloud.user.model.Password;
import com.ebanma.cloud.user.model.SMSCode;
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
    public Result login(UserLogin userLogin) {
        System.out.println(userLogin);
        if(userLogin.getType().equals(UserLoginEnum.APP_PHONE_LOGIN.getType())){
            return loginService.appCodeLogin(userLogin);
        }else if(userLogin.getType().equals(UserLoginEnum.APP_PASSWORD_LOGIN.getType())){
            return loginService.appPasswordLogin(userLogin);
        }else if(userLogin.getType().equals(UserLoginEnum.PLATFORM_PASSWORD_LOGIN.getType())){
            return loginService.platformPasswordLogin(userLogin);
        }
        return ResultGenerator.genFailResult("登陆非法");
    }

    @PostMapping("/getSmsCode")
    public Result getSMSCode(SMSCode smsCode){

        return loginService.getSMSCode(smsCode);
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(Password password){
        return loginService.updatePassword(password);
    }

    @GetMapping("/admin/getUserInfoByToken")
    public String getUserIdByToken(){
        return BaseContextHandler.getUserId();
    };

    @PostMapping("/checkCode")
    public Result checkCode(Password password){
        return loginService.checkCode(password);
    }
}
