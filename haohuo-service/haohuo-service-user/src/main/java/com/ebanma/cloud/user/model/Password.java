package com.ebanma.cloud.user.model;

/**
 * @author yuqintao
 * @version $ Id: Password, v 0.1 2023/06/08 8:55 banma-0018 Exp $
 */
public class Password {

    private String userPhone;

    private String newPassword;

    private Boolean createNewUser;

    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    @Override
    public String toString() {
        return "Password{" +
                "userPhone='" + userPhone + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", createNewUser=" + createNewUser +
                ", smsCode='" + smsCode + '\'' +
                '}';
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public Boolean getCreateNewUser() {
        return createNewUser;
    }

    public void setCreateNewUser(Boolean createNewUser) {
        this.createNewUser = createNewUser;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }



    public Password() {
    }


}
