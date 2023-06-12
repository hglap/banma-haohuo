package com.ebanma.cloud.game.service;

import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;

public interface GameService {

    /**
     * 抽奖--获取抽奖结果
     *
     * @param gameDrawDto 游戏画dto
     * @return {@link GamePrizeVO}
     */
    GamePrizeVO result(GameDrawDto gameDrawDto);

    /**
     * 购买砸蛋次数
     *
     * @param gamePurchasesDto 游戏购买dto
     * @return boolean
     */
    boolean purchases(GamePurchasesDto gamePurchasesDto);




}
