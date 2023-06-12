package com.ebanma.cloud.seckill.service;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.game.api.vo.GameEggRuleVO;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 崔国垲
 * @version $ Id: FeignGateWay, v 0.1 2023/06/12 10:37 banma-0197 Exp $
 */
public interface FeignServiceGateway {


    Result<List<GameEggRuleVO>> percentage();

    /**
     * 账务增删，包含积分和红包
     *
     * @param transAccountLog
     * @return
     */
    Result updateTrans(TransAccountLog transAccountLog);
}
