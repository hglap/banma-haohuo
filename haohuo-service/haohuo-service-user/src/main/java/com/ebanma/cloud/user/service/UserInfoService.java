package com.ebanma.cloud.user.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.vo.UserInfoVO;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface UserInfoService extends Service<UserInfo> {

    UserInfoVO getUserInfo(String userId);

}
