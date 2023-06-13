package com.ebanma.cloud.game.service;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.game.model.po.GameRule;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;

import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameRuleService extends Service<GameRule> {

    /**
     * 获取游戏规则
     * 获取游戏概率控制 [根据道具进行修正]
     * 根据道具使用情况,分别进行概率修正
     *
     * @param userInfoVO 用户信息签证官
     * @param propType   支持类型
     * @return {@link List}<{@link GameEggRuleVO}>
     */
    List<GameEggRuleVO> getGameRules(GameUserInfo userInfoVO , int propType);

    /**
     * 获取游戏概率控制
     *
     * @return {@link List}<{@link GameEggRuleVO}>
     */
    List<GameEggRuleVO> getGameRules();

    /**
     * 砸蛋抽奖
     * 结构为抽中蛋类型的奖品概率控制list
     *
     * @param eggRuleVOS 蛋抽奖概率规则vos
     * @return {@link GameEggRuleVO}
     */
    GameEggRuleVO getEggDraw(List<GameEggRuleVO> eggRuleVOS);

    /**
     * 奖品抽奖
     * 返回结果为奖品类型
     *
     * @param presentRuleVOS 奖品概率规则vos
     * @return {@link GamePresentRuleVO}
     */
    GamePresentRuleVO getPresentDraw(List<GamePresentRuleVO> presentRuleVOS);

    /**
     * 砸蛋抽奖
     * 对金蛋进行保底控制
     *
     * @param gameRules 游戏规则
     * @return {@link GameEggRuleVO}
     */
    GameEggRuleVO getEggDrawByGuaranteed(List<GameEggRuleVO> gameRules);


    /**
     * 金蛋保底回滚
     *
     * @param eggDraw 鸡蛋画
     */
    void guaranteedRollback(GameEggRuleVO eggDraw);
}
