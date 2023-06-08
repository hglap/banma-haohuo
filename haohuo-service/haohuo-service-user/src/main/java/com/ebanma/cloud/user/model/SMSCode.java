package com.ebanma.cloud.user.model;

/**
 * @author yuqintao
 * @version $ Id: SMSCode, v 0.1 2023/06/07 20:11 banma-0018 Exp $
 */
public class SMSCode {

    private String userPhone;

    //login-登陆，pass-密码服务
    private String type;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SMSCode() {
    }

    public SMSCode(String userPhone, String type) {
        this.userPhone = userPhone;
        this.type = type;
    }
}
