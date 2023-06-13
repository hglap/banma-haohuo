package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.model.dto.GameDrawDto;
import com.ebanma.cloud.game.model.dto.GamePurchasesDto;
import com.ebanma.cloud.game.model.vo.GameDrawVO;
import com.ebanma.cloud.game.model.vo.GamePrizeVO;
import com.ebanma.cloud.game.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
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



    private RedisTemplate<String ,GameDrawVO> redisTemplate;


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
     * 抽奖--获取抽奖结果
     *
     * @return {@link Result}
     */
    @ApiOperation("抽奖--获取抽奖结果")
    @PostMapping("/resultTest")
    public Result resultTest() {
        try {
            GameDrawDto gameDrawDto = new GameDrawDto();
            String[] strs = {"100","101","102","103","104","105","106"};
            Random random = new Random();
            String userId = strs[random.nextInt(strs.length)];

            gameDrawDto.setUserId(userId);
            gameDrawDto.setPropCode(10);
            GamePrizeVO result = gameService.result(gameDrawDto);
            return ResultGenerator.genSuccessResult(result);
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

    @PostMapping("/test")
    public Result test(int remainTimes , int winning){
        GameDrawVO gameDrawVO =  redisTemplate.opsForValue().get(GameRedisEnum.DRAW_INFO.getKey());
        System.out.println(gameDrawVO);
        if (gameDrawVO != null) {
            gameDrawVO.setRemainTimes(remainTimes);
            gameDrawVO.setWinning(winning);
            redisTemplate.opsForValue().set(GameRedisEnum.DRAW_INFO.getKey(),gameDrawVO);
        }
        return ResultGenerator.genSuccessResult();
    }

}