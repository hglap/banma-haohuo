package com.ebanma.cloud.game.model.dto;

import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @author banma-
 * @version $ Id: GameDrawDto, v 0.1 2023/06/07 11:24 banma- Exp $
 */
@Data
@ApiModel( value ="GamePurchasesDto", description="购买砸蛋次数入参" )
public class GamePurchasesDto {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 道具类型
     */
    private int buyCount;

    public TransAccountLog getTransAccountLog() {
        TransAccountLog transAccountLog = new TransAccountLog();
        transAccountLog.setUserId(this.userId);
        transAccountLog.setAmount(100);
        transAccountLog.setLogType(1);
        transAccountLog.setBizType(GamePriceOrPropEnum.POINT.getValue());
        transAccountLog.setDescription("砸蛋活动，消耗100积分购买三次抽奖机会。");
        transAccountLog.setConsume("game");
        transAccountLog.setCreateOn(new Date());
        transAccountLog.setCreateBy(this.userId);
        return transAccountLog;
    }
}