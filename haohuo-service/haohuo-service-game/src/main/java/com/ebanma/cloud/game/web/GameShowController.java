package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.game.service.GameRuleService;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.service.GameUserRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author banma-
 * @version $ Id: GameOperationController, v 0.1 2023/06/06 16:31 banma- Exp $
 */
@RestController
@RequestMapping("/game/show")
@Api(value = "游戏" ,tags = "游戏")
public class GameShowController {

    @Resource
    private GameRuleService gameRuleService;

    @Resource
    private GameUserInfoService gameUserInfoService;

    @Resource
    private GameUserRecordService gameUserRecordService;

    /**
     * 获取游戏规则概率
     *
     * @return {@link Result}
     */
    @ApiOperation("获取游戏规则概率")
    @PostMapping("/percentage")
    public Result percentage() {
        return ResultGenerator.genSuccessResult(gameRuleService.getGameRules());
    };

    /**
     * 中奖滚动信息
     *
     * @return {@link Result}
     */
    @ApiOperation("中奖滚动信息")
    @PostMapping("/topRank")
    public Result topRank() {
        return ResultGenerator.genSuccessResult(gameUserRecordService.getTopRank());
    };

    /**
     * 游戏中奖排行榜
     *
     * @return {@link Result}
     */
    @ApiOperation("游戏中奖排行榜")
    @PostMapping("/rankingList")
    public Result rankingList() {
        return ResultGenerator.genSuccessResult(gameUserInfoService.getRankingList());
    };



}