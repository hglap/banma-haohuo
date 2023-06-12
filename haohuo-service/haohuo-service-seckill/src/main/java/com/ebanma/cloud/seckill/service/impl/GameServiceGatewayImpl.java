package com.ebanma.cloud.seckill.service.impl;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.game.api.openfeign.GameServiceFeign;
import com.ebanma.cloud.game.api.vo.GameEggRuleVO;
import com.ebanma.cloud.seckill.service.GameServiceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: GameServiceGatewayImpl, v 0.1 2023/06/12 10:41 banma-0197 Exp $
 */
@Service
@RequiredArgsConstructor
public class GameServiceGatewayImpl implements GameServiceGateway {

    private final GameServiceFeign gameServiceFeign;

    @Override
    public Result<List<GameEggRuleVO>> percentage() {
       return gameServiceFeign.percentage();
    }
}
