package com.ebanma.cloud.trans.api.dto;



import java.io.Serializable;

/**
 * 
 * @TableName address
 */

public class Address implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userid;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收获人电话
     */
    private String phone;

    /**
     * 收货人地址
     */
    private String addr;

    /**
     * 收货人邮编
     */
    private String code;

    /**
     * 0 默认 1非默认
     */
    private Integer isDefault;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}