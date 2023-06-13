package com.ebanma.cloud.game.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.service.GameUserInfoService;
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
@RequestMapping("/game/userInfo")
@Api(value = "游戏域个人信息" ,tags = "游戏域个人信息")
public class GameUserInfoController {

    @Resource
    private GameUserInfoService gameUserInfoService;

    /**
     * 获取抽奖界面详细信息
     *
     * @return {@link Result}
     */
    @ApiOperation("获取抽奖界面详细信息")
    @PostMapping("/details")
    public Result details( String userId) {
        return ResultGenerator.genSuccessResult( gameUserInfoService.details(userId));
    };
    /**
     * 获取抽奖参与记录
     *
     * @return {@link Result}
     */
    @ApiOperation("获取抽奖参与记录")
    @PostMapping("/record")
    public Result record( GameUserRecordDto gameUserRecordDto) {
        return ResultGenerator.genSuccessResult( gameUserInfoService.record(gameUserRecordDto));
    };

}