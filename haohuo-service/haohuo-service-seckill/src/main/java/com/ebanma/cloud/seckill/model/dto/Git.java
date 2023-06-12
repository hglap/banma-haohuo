package com.ebanma.cloud.seckill.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 崔国垲
 * @version $ Id: Git, v 0.1 2023/06/12 15:32 banma-0197 Exp $
 */
@Data
public class Git implements Serializable {

    private int bizType;

    private int amount;

    private String gitName;
}
