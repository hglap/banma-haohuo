package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.vo.GameDrawVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.GameService;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author banma-
 * @version $ Id: GameOperationController, v 0.1 2023/06/06 16:31 banma- Exp $
 */
@RestController
@RequestMapping("/game/operation")
@Api(value = "游戏" ,tags = "游戏")
public class GameOperationController {


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

    /**
     * 购买砸蛋次数
     *
     * @param gamePurchasesDto 游戏购买dto
     * @return {@link Result}
     */
    @ApiOperation("购买砸蛋次数")
    @PostMapping("/purchases")
    public Result purchases( GamePurchasesDto gamePurchasesDto) {
        return gameService.purchases(gamePurchasesDto) ?
                ResultGenerator.genSuccessResult("购买砸蛋次数成功")
                :ResultGenerator.genFailResult("购买砸蛋次数失败");
    };


}