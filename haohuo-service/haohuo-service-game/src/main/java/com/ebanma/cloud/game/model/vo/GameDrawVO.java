package com.ebanma.cloud.game.model.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 抽奖信息
 * 用于储存进redis，进行金蛋保底控制
 * @author banma-
 * @version $ Id: GameEggRuleVO, v 0.1 2023/06/06 22:11 banma- Exp $
 */
@Data
@ApiModel( value ="GameDrawVO", description="抽奖信息" )
@NoArgsConstructor
public class GameDrawVO implements Serializable {


    /**
     * 总次数
     */
    private Integer totalTimes;
    /**
     * 剩余次数
     */
    private Integer remainTimes;

    /**
     * 保底次数
     * 根据概率和保底次数进行计算
     */
    private Integer guaranteedTimes;


    /**
     * 是否已经出现金蛋
     */
    private Integer winning;

    public GameDrawVO(int totalTimes, Double eggOdd) {
        this.totalTimes = totalTimes;
        this.remainTimes = totalTimes;
        this.guaranteedTimes = new Long(Math.round(eggOdd*totalTimes)).intValue();
        this.winning = 0;
    }

    public GameDrawVO(Integer totalTimes, int guaranteedTimes) {
        this.totalTimes = totalTimes;
        this.remainTimes = totalTimes;
        this.guaranteedTimes = guaranteedTimes;
        this.winning = 0;
    }


    /**
     * 重置
     */
    public void resetting(){
        this.remainTimes = this.totalTimes;
        this.winning = 0;
    }

}