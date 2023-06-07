package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.util.JwtUtil;
import com.ebanma.cloud.user.dao.UserInfoMapper;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.model.UserLogin;
import com.ebanma.cloud.user.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yuqintao
 * @version $ Id: LoginServiceImpl, v 0.1 2023/06/07 10:13 banma-0018 Exp $
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Result appCodeLogin(UserLogin userLogin) {
        //todo:查询
        return null;
    }

    @Override
    public Result appPasswordLogin(UserLogin userLogin) {
        UserInfo userInfo = userInfoMapper.selectByPhone(userLogin.getUserPhone());
        if(userInfo == null){
            return ResultGenerator.genUnRegisterResult("该手机号未注册");
        }else if(!userInfo.getPassword().equals(userLogin.getPassword())){
            return ResultGenerator.genFailResult("密码错误");
        }
        //todo:生成token
        String token = JwtUtil.createJWT("appLogin",userLogin.getUserPhone(),null);
        return ResultGenerator.genSuccessResult(token);
    }

    @Override
    public Result platformPasswordLogin(UserLogin userLogin) {
        UserInfo userInfo = userInfoMapper.selectByPhone(userLogin.getUserPhone());
        if(userInfo == null){
            return ResultGenerator.genUnRegisterResult("该手机号未注册");
        }else if(!userInfo.getPassword().equals(userLogin.getPassword())){
            return ResultGenerator.genFailResult("密码错误");
        }
        //todo:生成token
        String token = JwtUtil.createJWT("appLogin",userLogin.getUserPhone(),null);
        return ResultGenerator.genSuccessResult(token);
    }
}
