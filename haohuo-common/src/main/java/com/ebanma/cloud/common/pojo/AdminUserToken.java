package com.ebanma.cloud.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUserToken implements Serializable {
    private Long adminUserId;

    private String token;
}