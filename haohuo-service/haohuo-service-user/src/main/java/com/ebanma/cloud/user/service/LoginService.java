package com.ebanma.cloud.user.service;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.user.model.Password;
import com.ebanma.cloud.user.model.SMSCode;
import com.ebanma.cloud.user.model.UserLogin;

/**
 * @author yuqintao
 * @version $ Id: LoginService, v 0.1 2023/06/07 10:13 banma-0018 Exp $
 */
public interface LoginService {
    Result appCodeLogin(UserLogin userLogin);

    Result appPasswordLogin(UserLogin userLogin);

    Result platformPasswordLogin(UserLogin userLogin);

    Result getSMSCode(SMSCode smsCode);

    Result updatePassword(Password password);

}
