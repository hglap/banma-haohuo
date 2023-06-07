package com.ebanma.cloud.game.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.game.dto.GameDrawDto;
import com.ebanma.cloud.game.model.GameUserInfo;
import com.ebanma.cloud.game.model.GameUserProp;
import com.ebanma.cloud.game.service.GameService;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.service.GameUserPropService;
import com.ebanma.cloud.game.vo.GameUserInfoVO;
import com.ebanma.cloud.game.vo.GamePrizeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.locks.Condition;

/**
 * @author banma-
 * @version $ Id: GameServiceImpl, v 0.1 2023/06/06 16:32 banma- Exp $
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {


    @Resource
    private GameUserInfoService gameUserInfoService;

    @Resource
    private GameUserPropService gameUserPropService;


    @Override
    public GameUserInfoVO details(String userId) {
        //查询用户游戏域个人信息
        GameUserInfo userInfo = gameUserInfoService.getOne( new QueryWrapper<GameUserInfo>().eq("user_id", userId));
        if(userInfo == null) {
            return null;
        }
        GameUserInfoVO gameUserInfoVO = new GameUserInfoVO(userInfo);
        //个人中心获取用户积分
        gameUserInfoVO.setUserPoints(null);
        //查询获取用户道具
        gameUserInfoVO.setGameUserProp(gameUserPropService.list(new QueryWrapper<GameUserProp>().eq("user_id", userId)));

        //
        return null;
    }

    @Override
    public GamePrizeVO result(GameDrawDto gameDrawDto) {
        return null;
    }
}