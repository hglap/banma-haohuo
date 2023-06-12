package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.dao.GameUserInfoMapper;
import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameUserInfoVO;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.service.GameUserRecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserInfoServiceImpl extends AbstractService<GameUserInfo> implements GameUserInfoService {

    @Resource
    private GameUserInfoMapper gameUserInfoMapper;

    @Resource
    private RedisTemplate<String, GameUserInfo> redisTemplate;

    @Resource
    GameUserRecordService gameUserRecordService;

    @Override
    public GameUserInfoVO details(String userId) {
        //1.获取用户信息
        GameUserInfo userInfo = this.getUserInfo(userId);
        //2.类型转换
        GameUserInfoVO vo = new GameUserInfoVO(userInfo);
        //2.个人中心获取用户积分
        //todo

        vo.setUserPoints(0);

        return vo;
    }

    @Override
    public PageInfo<GameUserRecord> record(GameUserRecordDto gameUserRecordDto) {
        return gameUserRecordService.record(gameUserRecordDto);
    }

    @Override
    public GameUserInfo getUserInfo(String userId) {
        //1.Redis获取用户信息
        GameUserInfo userInfo = redisTemplate.opsForValue().get(GameRedisEnum.USER_INFO.getKey()+userId);
        if( userInfo != null ){
            return userInfo;
        }

        //2.redis中没有储存,从数据库查询用户游戏域个人信息
        userInfo = gameUserInfoMapper.getUserInfo(userId);
        if(userInfo == null) {
            userInfo = new GameUserInfo(userId);
            gameUserInfoMapper.insert(userInfo);
        }

        //3.将用户信息存进redis中,提升后续用户抽奖时效率[5分钟]
        redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+userId,userInfo,5, TimeUnit.MINUTES);
        return userInfo;
    }


}
