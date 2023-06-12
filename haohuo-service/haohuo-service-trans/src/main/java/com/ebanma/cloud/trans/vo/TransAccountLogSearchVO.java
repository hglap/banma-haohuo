package com.ebanma.cloud.trans.vo;

import lombok.Data;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogSearchVO, v 0.1 2023/06/07 19:57 banma-0193 Exp $
 */
@Data
public class TransAccountLogSearchVO {
    //用户id（可不填，从token获取）
    private String userId;

    //流水类型 0获取 1消耗
    private Integer logType;

    //代币类型 0红包 1积分
    private Integer bizType;

    //红包状态 0未使用 1已使用 2已过期 3七日内过期
    private Integer redPacketStatus;
}
