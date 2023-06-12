package com.ebanma.cloud.user.model;

import lombok.Data;

/**
 * @author yuqintao
 * @version $ Id: LoginVO, v 0.1 2023/06/12 13:50 banma-0018 Exp $
 */
@Data
public class LoginVO {

    private String access_token;

    private String scope;

    private String token_type;

    private String expires_in;

}
