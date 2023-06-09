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

    private String code;

    private String loginIP;

    private String loginLocation;

    private String exploreType;

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

    public UserLogin(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getExploreType() {
        return exploreType;
    }

    public void setExploreType(String exploreType) {
        this.exploreType = exploreType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserLogin(String userPhone, String type, String password, String code, String loginIP, String loginLocation, String exploreType) {
        this.userPhone = userPhone;
        this.type = type;
        this.password = password;
        this.code = code;
        this.loginIP = loginIP;
        this.loginLocation = loginLocation;
        this.exploreType = exploreType;
    }
}
