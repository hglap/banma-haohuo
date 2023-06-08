package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.service.GameService;
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
@RequestMapping("/game")
@Api(value = "游戏" ,tags = "游戏")
public class GameController {

    @Resource
    private GameService gameService;

    /**
     * 抽奖--获取抽奖结果
     *
     * @return {@link Result}
     */
    @ApiOperation("抽奖--获取抽奖结果")
    @PostMapping("/result")
    public Result result(GameDrawDto gameDrawDto) {
        try {
            return ResultGenerator.genSuccessResult( gameService.result(gameDrawDto));
        }catch (Exception e) {
            return ResultGenerator.genFailResult(e.getMessage());
        }
    };

}