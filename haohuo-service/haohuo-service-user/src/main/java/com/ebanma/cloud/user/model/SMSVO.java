package com.ebanma.cloud.user.model;

import lombok.Data;

/**
 * @author yuqintao
 * @version $ Id: SMSVO, v 0.1 2023/06/12 13:48 banma-0018 Exp $
 */
@Data
public class SMSVO {

    private String smsCode;

    private Integer remainTime;

    private String message;

}
