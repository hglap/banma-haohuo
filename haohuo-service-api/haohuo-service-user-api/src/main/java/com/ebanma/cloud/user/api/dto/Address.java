package com.ebanma.cloud.user.api.dto;


import com.ebanma.cloud.user.utils.InsertGroup;
import com.ebanma.cloud.user.utils.UpdateGroup;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Address {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "主键id不能为空", groups = UpdateGroup.class)
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    @NotBlank(message = "用户id不能为空", groups = InsertGroup.class)
    private String userId;

    /**
     * 省
     */
    @NotBlank(message = "省份不能为空", groups = InsertGroup.class)
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "城市不能为空", groups = InsertGroup.class)
    private String city;

    /**
     * 县/区
     */
    @NotBlank(message = "区县不能为空", groups = InsertGroup.class)
    private String area;

    /**
     * 电话
     */
    @NotBlank(message = "电话不能为空", groups = InsertGroup.class)
    private String phone;

    /**
     * 详细地址
     */
    @NotBlank(message = "地址不能为空", groups = InsertGroup.class)
    private String address;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空", groups = InsertGroup.class)
    private String contact;

    /**
     * 是否是默认 1默认 0否
     */
    @Column(name = "is_default")
    private String isDefault;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取县/区
     *
     * @return area - 县/区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置县/区
     *
     * @param area 县/区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取电话
     *
     * @return phone - 电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取是否是默认 1默认 0否
     *
     * @return is_default - 是否是默认 1默认 0否
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否是默认 1默认 0否
     *
     * @param isDefault 是否是默认 1默认 0否
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }
}