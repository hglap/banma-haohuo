package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.dao.GameUserInfoMapper;
import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserProp;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameRankingVO;
import com.ebanma.cloud.game.model.vo.GameUserInfoVO;
import com.ebanma.cloud.game.service.GameUserInfoService;
import com.ebanma.cloud.game.service.GameUserPropService;
import com.ebanma.cloud.game.service.GameUserRecordService;
import com.ebanma.cloud.game.service.TransService;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserInfoServiceImpl extends AbstractService<GameUserInfo> implements GameUserInfoService {


    @Resource
    private TransService transService;
    @Resource
    private GameUserInfoMapper gameUserInfoMapper;

    @Resource
    private RedisTemplate<String, GameUserInfo> redisTemplate;

    @Resource
    private GameUserRecordService gameUserRecordService;

    @Resource
    private GameUserPropService gameUserPropServicel;

    @Override
    public GameUserInfoVO details(String userId) {
        //1.获取用户信息
        GameUserInfo userInfo = this.getUserInfo(userId);
        //2.类型转换
        GameUserInfoVO vo = new GameUserInfoVO(userInfo);
        //2.个人中心获取用户积分
        //todo
        vo.setUserPoints(transService.getPointInfo(userId).intValue());
        return vo;
    }

    @Override
    public PageInfo<GameUserRecord> record(GameUserRecordDto gameUserRecordDto) {
        return gameUserRecordService.record(gameUserRecordDto);
    }

    @Override
    @Transactional
    public GameUserInfo getUserInfo(String userId) {
        //1.Redis获取用户信息
        GameUserInfo userInfo = redisTemplate.opsForValue().get(GameRedisEnum.USER_INFO.getKey()+userId);
        if( userInfo != null ){
            //清除道具使用记录
            userInfo.getUseGameUserProp().clear();
            return userInfo;
        }

        //2.redis中没有储存,从数据库查询用户游戏域个人信息
        userInfo = gameUserInfoMapper.getUserInfo(userId);
        if(userInfo == null) {
            //新增用户信息
            userInfo = new GameUserInfo(userId);
            gameUserInfoMapper.insert(userInfo);
            //新增用户道具信息
            List<GameUserProp> props = new ArrayList<>();
            props.add(new GameUserProp(userId, GamePriceOrPropEnum.A.getValue()));
            props.add(new GameUserProp(userId, GamePriceOrPropEnum.B.getValue()));
            props.add(new GameUserProp(userId, GamePriceOrPropEnum.C.getValue()));
            userInfo.setGameUserProp(props);
            props.forEach( m->{
                gameUserPropServicel.save(m);
            });
        }

        //3.将用户信息存进redis中,提升后续用户抽奖时效率[5分钟]
        redisTemplate.opsForValue().set(GameRedisEnum.USER_INFO.getKey()+userId,userInfo,5, TimeUnit.MINUTES);
        return userInfo;
    }


    @Override
    public GameRankingVO getRankingList() {
        GameRankingVO result = gameUserInfoMapper.getRanking();
        result.setRankingList(gameUserInfoMapper.getRankingList());
        return result;
    }
}
