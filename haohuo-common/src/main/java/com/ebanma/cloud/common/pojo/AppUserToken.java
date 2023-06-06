package com.ebanma.cloud.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppUserToken implements Serializable {
    private Long userId;

    private String token;

}