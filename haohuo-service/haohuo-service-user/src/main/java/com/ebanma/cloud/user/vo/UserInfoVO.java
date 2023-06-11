package com.ebanma.cloud.user.vo;

import com.ebanma.cloud.user.model.Address;

import java.util.List;

/**
 * @author 鹿胜宝
 * @version $ Id: userInfoVO, v 0.1 2023/06/11 15:24 banma-0193 Exp $
 */
public class UserInfoVO {
    /**
     * 收货地址列表
     */
    private List<Address> addressList;
    /**
     * 头像
     */
    private String avator;
    /**
     * 生日
     */
    private String birth;
    /**
     * 城市
     */
    private String city;
    /**
     * 升级距离
     */
    private String dayToUp;
    /**
     * 成长等级
     */
    private String growLevel;
    /**
     * 职业
     */
    private String job;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 积分
     */
    private String points;
    /**
     * 性别，1男0女
     */
    private long sex;
    /**
     * 购物等级
     */
    private String shoppingLevel;
    /**
     * 签名
     */
    private String sign;
    /**
     * 用户名
     */
    private String userId;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDayToUp() {
        return dayToUp;
    }

    public void setDayToUp(String dayToUp) {
        this.dayToUp = dayToUp;
    }

    public String getGrowLevel() {
        return growLevel;
    }

    public void setGrowLevel(String growLevel) {
        this.growLevel = growLevel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public long getSex() {
        return sex;
    }

    public void setSex(long sex) {
        this.sex = sex;
    }

    public String getShoppingLevel() {
        return shoppingLevel;
    }

    public void setShoppingLevel(String shoppingLevel) {
        this.shoppingLevel = shoppingLevel;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
