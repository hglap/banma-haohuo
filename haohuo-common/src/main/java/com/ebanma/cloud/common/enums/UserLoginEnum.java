package com.ebanma.cloud.common.enums;

/**
 * @author yuqintao
 * @version $ Id: UserLoginEnum, v 0.1 2023/06/06 16:57 banma-0018 Exp $
 */
public enum UserLoginEnum {


    APP_PHONE_LOGIN("app_phone_login"),

    APP_PASSWORD_LOGIN("app_password_login"),

    PLATFORM_PASSWORD_LOGIN("platform_password_login");
    

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    UserLoginEnum(String type) {
        this.type = type;
    }

    UserLoginEnum() {

    }

}
