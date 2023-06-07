package com.ebanma.cloud.user.model;

import com.ebanma.cloud.common.enums.UserLoginEnum;

/**
 * @author yuqintao
 * @version $ Id: UserLogin, v 0.1 2023/06/06 16:53 banma-0018 Exp $
 */

public class UserLogin {

    private String userPhone;

    private String type;

    private String password;

    private String SMSCode;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSMSCode() {
        return SMSCode;
    }

    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }

    public UserLogin(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserLogin(String userPhone, String type, String password, String SMSCode) {
        this.userPhone = userPhone;
        this.type = type;
        this.password = password;
        this.SMSCode = SMSCode;
    }
}
