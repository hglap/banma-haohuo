package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.service.GameRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author banma-
 * @version $ Id: GameController, v 0.1 2023/06/06 16:31 banma- Exp $
 */
@RestController
@RequestMapping("/game/show")
@Api(value = "游戏" ,tags = "游戏")
public class GameShowController {

    @Resource
    private GameRuleService gameRuleService;

    /**
     * 获取游戏规则概率
     *
     * @return {@link Result}
     */
    @ApiOperation("获取游戏规则概率")
    @PostMapping("/percentage")
    public Result percentage(GameDrawDto gameDrawDto) {
        return ResultGenerator.genSuccessResult(gameRuleService.getGameRules());
    };

}