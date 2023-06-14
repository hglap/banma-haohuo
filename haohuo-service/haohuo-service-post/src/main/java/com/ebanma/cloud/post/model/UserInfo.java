package com.ebanma.cloud.post.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 刘宇
 * @version $ Id: UserInfo, v 0.1 2023/06/07 20:57 banma-0194 Exp $
 */
public class UserInfo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号即用户编号userID
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像url
     */
    private String avator;

    /**
     * 购物次数
     */
    private Integer shoppingtimes;

    /**
     * 成长等级
     */
    private Integer growlevel;

    /**
     * 购物等级
     */
    private Integer shoppinglevel;

    /**
     * 登录次数
     */
    private Integer logintimes;

    /**
     * 短信验证码
     */
    private Integer smscode;

    /**
     * 总积分
     */
    private String points;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职业
     */
    private String job;

    /**
     * 个性签名
     */
    private String sign;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户标签
     */
    private String tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Integer getShoppingtimes() {
        return shoppingtimes;
    }

    public void setShoppingtimes(Integer shoppingtimes) {
        this.shoppingtimes = shoppingtimes;
    }

    public Integer getGrowlevel() {
        return growlevel;
    }

    public void setGrowlevel(Integer growlevel) {
        this.growlevel = growlevel;
    }

    public Integer getShoppinglevel() {
        return shoppinglevel;
    }

    public void setShoppinglevel(Integer shoppinglevel) {
        this.shoppinglevel = shoppinglevel;
    }

    public Integer getLogintimes() {
        return logintimes;
    }

    public void setLogintimes(Integer logintimes) {
        this.logintimes = logintimes;
    }

    public Integer getSmscode() {
        return smscode;
    }

    public void setSmscode(Integer smscode) {
        this.smscode = smscode;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public UserInfo() {
    }
}
