package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.user.dao.UserInfoMapper;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

}
