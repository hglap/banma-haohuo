package com.ebanma.cloud.game.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.game.model.po.GameRule;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;

import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameRuleService extends IService<GameRule> {

    List<GameEggRuleVO> getGameRules();

    GameEggRuleVO getEggDraw(List<GameEggRuleVO> eggRuleVOS);

    GamePresentRuleVO getPresentDraw(List<GamePresentRuleVO> presentRuleVOS);

}
