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
    private RedisUtil<String ,GameDrawVO> redisUtil;

    @Resource
    private GameUserInfoService gameUserInfoService;

    @Resource
    private GameService gameService;

    private static final String[] STR = {"100","101","102","103","104","105","106","107","108","109",
            "110","111","112","113","114","115","116","117","118","119",
            "120","121","122","123","124","125","126","127","128","129",
            "130","131","132","133","134","135","136","137","138","139",
            "140","141","142","143","144","145","146","147","148","149",
            "150","151","152","153","154","155","156","157","158","159"};


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
    public Result resultTest( int num) {
        try {
            GameDrawDto gameDrawDto = new GameDrawDto();
            Random random = new Random();
            String userId = STR[random.nextInt(STR.length)];
//            String userId = String.valueOf(random.nextInt(num));
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
    public Result test(int num ){
//        GameDrawVO gameDrawVO =  redisUtil.get(GameRedisEnum.DRAW_INFO.getKey());
//        System.out.println(gameDrawVO);
//        if (gameDrawVO != null) {
//            gameDrawVO.setRemainTimes(remainTimes);
//            gameDrawVO.setWinning(winning);
//            redisUtil.set(GameRedisEnum.DRAW_INFO.getKey(),gameDrawVO);
//        }
        for (int i = 0; i < num; i++) {
            gameUserInfoService.getUserInfo(String.valueOf(i));
        }
        return ResultGenerator.genSuccessResult();
    }

}