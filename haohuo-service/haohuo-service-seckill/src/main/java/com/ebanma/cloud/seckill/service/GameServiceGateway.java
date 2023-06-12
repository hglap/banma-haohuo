package com.ebanma.cloud.seckill.service;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.game.api.vo.GameEggRuleVO;

import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: FeignGateWay, v 0.1 2023/06/12 10:37 banma-0197 Exp $
 */
public interface GameServiceGateway {


    Result<List<GameEggRuleVO>> percentage();
}
