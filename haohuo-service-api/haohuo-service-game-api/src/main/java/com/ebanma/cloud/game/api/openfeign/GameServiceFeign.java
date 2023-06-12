package com.ebanma.cloud.game.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.game.api.vo.GameEggRuleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author banma-
 * @version $ Id: GameServiceFeign, v 0.1 2023/06/12 10:00 banma- Exp $
 */
@FeignClient(value = "game-service" ,path="/game/show")
public interface GameServiceFeign {

    @PostMapping("/percentage")
    Result<List<GameEggRuleVO>> percentage() ;

}