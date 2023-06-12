package com.ebanma.cloud.user.service.impl;

import cn.hutool.http.HttpRequest;
import com.alibaba.nacos.common.utils.StringUtils;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.util.JwtUtil;
import com.ebanma.cloud.common.util.NumberUtil;
import com.ebanma.cloud.user.dao.UserInfoMapper;
import com.ebanma.cloud.user.model.*;
import com.ebanma.cloud.user.service.LoginService;
import com.ebanma.cloud.user.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author yuqintao
 * @version $ Id: LoginServiceImpl, v 0.1 2023/06/07 10:13 banma-0018 Exp $
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoMapper userInfoMapper;

    private final String SMS_CODE_KEY_LOGIN = "smsCode:login";

    private final String SMS_CODE_KEY_PASSWORD = "smsCode:password";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result appCodeLogin(UserLogin userLogin) {
        UserInfo userInfo = userInfoMapper.selectByPhone(userLogin.getUserPhone());
        if(userInfo == null){
            return ResultGenerator.genUnRegisterResult("该手机号未注册");
        }
        //todo:使用redis获取生成的验证码
        String smsCode = (String) redisUtil.get(SMS_CODE_KEY_LOGIN+userLogin.getUserPhone());
        if(StringUtils.isBlank(smsCode)){
            return ResultGenerator.genFailResult("请重新获取验证码");
        }
        if(userLogin.getCode().equals(smsCode)){
            String token = JwtUtil.createJWT("appLogin",userLogin.getUserPhone(),null);
            redisUtil.set(token,"OK");
            LoginVO loginVO = new LoginVO();
            loginVO.setAccess_token(token);
            return ResultGenerator.genSuccessResult(loginVO);
        }
        return ResultGenerator.genFailResult("验证码错误");
    }

    @Override
    public Result appPasswordLogin(UserLogin userLogin) {
        UserInfo userInfo = userInfoMapper.selectByPhone(userLogin.getUserPhone());
        if(userInfo == null){
            return ResultGenerator.genUnRegisterResult("该手机号未注册");
        }else if(!userInfo.getPassword().equals(userLogin.getPassword())){
            return ResultGenerator.genFailResult("密码错误");
        }
        String token = JwtUtil.createJWT("appLogin",userLogin.getUserPhone(),null);
        redisUtil.set(token,"OK");
        LoginVO loginVO = new LoginVO();
        loginVO.setAccess_token(token);
        return ResultGenerator.genSuccessResult(loginVO);
    }

    @Override
    public Result platformPasswordLogin(UserLogin userLogin) {
        UserInfo userInfo = userInfoMapper.selectByPhone(userLogin.getUserPhone());
        if(userInfo == null){
            return ResultGenerator.genUnRegisterResult("该手机号未注册");
        }else if(!userInfo.getPassword().equals(userLogin.getPassword())){
            return ResultGenerator.genFailResult("密码错误");
        }
        String token = JwtUtil.createJWT("platformLogin",userLogin.getUserPhone(),null);
        redisUtil.set(token,"OK");
        LoginVO loginVO = new LoginVO();
        loginVO.setAccess_token(token);
        return ResultGenerator.genSuccessResult(loginVO);
    }

    @Override
    public Result getSMSCode(SMSCode smsCode) {
        //手机号校验
        if(StringUtils.isBlank(smsCode.getUserPhone()) || smsCode.getUserPhone().matches("^[3-9]\\d{9}$")){
            return ResultGenerator.genFailResult("手机号不合法");
        }
        //根据不同用处在redis中设置验证码
        if(smsCode.getType().equals("login")){
            //登录
            UserInfo userInfo = userInfoMapper.selectByPhone(smsCode.getUserPhone());
            if(userInfo == null) {
                //第一次登录
                String code = NumberUtil.genRandomNum(6)+"";
                redisUtil.set(SMS_CODE_KEY_LOGIN+smsCode.getUserPhone(),code,5l, TimeUnit.MINUTES);
                SMSVO smsvo = new SMSVO();
                smsvo.setSmsCode(code);
                return ResultGenerator.genFirstLoginResult("第一次登录",smsvo);
            }else{
                //非第一次登录
                String code = NumberUtil.genRandomNum(6)+"";
                redisUtil.set(SMS_CODE_KEY_LOGIN+smsCode.getUserPhone(),code,5l, TimeUnit.MINUTES);
                SMSVO smsvo = new SMSVO();
                smsvo.setSmsCode(code);
                return ResultGenerator.genSuccessResult(smsvo);
            }
        } else if (smsCode.getType().equals("pass")) {
            //密码服务
            String code = NumberUtil.genRandomNum(6)+"";
            redisUtil.set(SMS_CODE_KEY_PASSWORD+smsCode.getUserPhone(),code,5l, TimeUnit.MINUTES);
            //TODO:redis设置验证码
            SMSVO smsvo = new SMSVO();
            smsvo.setSmsCode(code);
            return ResultGenerator.genSuccessResult(smsvo);
        }
        return ResultGenerator.genFailResult("获取验证码非法");
    }

    @Override
    public Result updatePassword(Password password) {

        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(password.getNewPassword());

        if(password.getCreateNewUser()){
            //新生成的账号，不需要校验验证码
            userInfo.setUserId(password.getUserPhone());
            userInfo.setUserPhone(password.getUserPhone());
            userInfo.setCreateTime(new Date());
            userInfoMapper.insertSelective(userInfo);
        }else{
            //非新生成账号，校验验证码
            //判断账号是否存在
            UserInfo userDetail = userInfoMapper.selectByPhone(password.getUserPhone());
            if(userDetail != null){
                userInfo.setId(userDetail.getId());
                //todo:从redis获取验证码
                String token = (String)redisUtil.get(SMS_CODE_KEY_PASSWORD+password.getUserPhone());
                if(StringUtils.isBlank(token)){
                    return ResultGenerator.genFailResult("请重新获取验证码");
                }
                if(password.getSMSCode().equals(token)){
                    //校验通过
                    userInfoMapper.updateByPrimaryKey(userInfo);
                    return ResultGenerator.genSuccessResult("注册成功");
                }else{
                    //校验不通过
                    return ResultGenerator.genFailResult("验证码错误");
                }
            }else{
                return ResultGenerator.genFailResult("该手机号不存在");
            }
        }
        return null;
    }

}
