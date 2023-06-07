package com.ebanma.cloud.game.service;

import com.ebanma.cloud.game.dto.GameDrawDto;
import com.ebanma.cloud.game.vo.GameUserInfoVO;
import com.ebanma.cloud.game.vo.GamePrizeVO;

public interface GameService {

    /**
     * 细节
     * 获取抽奖界面详细信息
     *
     * @param userId 用户id
     * @return {@link GameUserInfoVO}
     */
    GameUserInfoVO details(String userId);

    /**
     * 结果
     * 抽奖--获取抽奖结果
     *
     * @param gameDrawDto 游戏画dto
     * @return {@link GamePrizeVO}
     */
    GamePrizeVO result(GameDrawDto gameDrawDto);
}
