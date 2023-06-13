package com.ebanma.cloud.seckill.service.impl;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.game.api.openfeign.GameServiceFeign;
import com.ebanma.cloud.game.api.vo.GameEggRuleVO;
import com.ebanma.cloud.seckill.service.FeignServiceGateway;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import com.ebanma.cloud.trans.api.openfeign.TransFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: GameServiceGatewayImpl, v 0.1 2023/06/12 10:41 banma-0197 Exp $
 */
@Service
@RequiredArgsConstructor
public class FeignServiceGatewayImpl implements FeignServiceGateway {

    private final GameServiceFeign gameServiceFeign;

    private final TransFeign transFeign;

    @Override
    public Result<List<GameEggRuleVO>> percentage() {
       return gameServiceFeign.percentage();
    }

    @Override
    public Result updateTrans(TransAccountLog transAccountLog) {
        return transFeign.updateTrans(transAccountLog);
    }
}
