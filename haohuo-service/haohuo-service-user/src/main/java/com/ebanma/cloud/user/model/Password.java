package com.ebanma.cloud.user.model;

/**
 * @author yuqintao
 * @version $ Id: Password, v 0.1 2023/06/08 8:55 banma-0018 Exp $
 */
public class Password {

    private String userPhone;

    private String newPassword;

    private String SMSCode;

    private Boolean createNewUser;

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

    public String getSMSCode() {
        return SMSCode;
    }

    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }

    public Password() {
    }

    public Password(String userPhone, String newPassword, String SMSCode, Boolean createNewUser) {
        this.userPhone = userPhone;
        this.newPassword = newPassword;
        this.SMSCode = SMSCode;
        this.createNewUser = createNewUser;
    }
}
