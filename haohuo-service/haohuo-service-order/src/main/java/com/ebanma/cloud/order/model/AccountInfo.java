package com.ebanma.cloud.order.model;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountInfo {

    private BigDecimal totalPrice;

    private BigDecimal freight;

    private Integer totalIntegral;

    private Integer moneyDeduction;

    private BigDecimal redPacket;

    private BigDecimal redPacketDeduction;

    private BigDecimal totalAmount;

    private Integer integralLimit;

    private boolean scoreUsed;
}
