package com.ebanma.cloud.game.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.game.dao.GameRuleMapper;
import com.ebanma.cloud.game.model.GameRule;
import com.ebanma.cloud.game.service.GameRuleService;
import com.ebanma.cloud.game.util.AliasMethod;
import com.ebanma.cloud.game.vo.GameEggRuleVO;
import com.ebanma.cloud.game.vo.GamePresentRuleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameRuleServiceImpl extends ServiceImpl<BaseMapper<GameRule>,GameRule> implements GameRuleService {


    public static final String GAME_RULE="GameRules";

    @Resource
    private GameRuleMapper gameRuleMapper;

    @Resource
    private RedisTemplate<String, List<GameEggRuleVO>> redisTemplate;


    /**
     * 获取游戏规则
     * 1.首先从Redis中获取,
     * 2.Redis中没有数据时,进行数据库查询,数据处理并缓存进Redis
     *
     * @return {@link List}<{@link GameEggRuleVO}>
     */
    @Override
    public List<GameEggRuleVO> getGameRules() {
        //1.从从Redis中获取游戏规则
        List<GameEggRuleVO> gameEggRuleVOS = redisTemplate.opsForValue().get(GAME_RULE);
        //2.判断redis里是否存在,如果没有进行数据库查询
        if( gameEggRuleVOS == null ){
            //2.1查询
            Map<String, List<GameRule>> map = this.list().stream().collect(Collectors.groupingBy(GameRule::getEggType));
            //2.2数据处理
            List<GameEggRuleVO> newGameEggRuleVOS = new ArrayList<>();
            map.forEach( (kye, value) -> {
                newGameEggRuleVOS.add( new GameEggRuleVO(value) );
            });
            //2.3储存进Redis
            if ( !CollectionUtils.isEmpty(newGameEggRuleVOS) ) {
                redisTemplate.opsForValue().set( GAME_RULE ,newGameEggRuleVOS);
            }
            gameEggRuleVOS=newGameEggRuleVOS;
        }
        return gameEggRuleVOS;
    }

    /**
     * 蛋抽奖
     *
     * @param eggRuleVOS 蛋规则vos
     * @return {@link GameEggRuleVO}
     */
    @Override
    public GameEggRuleVO getEggDraw(List<GameEggRuleVO> eggRuleVOS) {
        if ( CollectionUtils.isEmpty(eggRuleVOS)){
            throw new ServiceException("未查询到抽奖规则配置");
        }
        List<Double> list = eggRuleVOS.stream().map(GameEggRuleVO :: getEggOdd).collect(Collectors.toList());
        AliasMethod method = new AliasMethod(list);
        return eggRuleVOS.get(method.next());
    }

    /**
     * 奖品抽奖
     *
     * @param presentRuleVOS 奖品规则vos
     * @return {@link GamePresentRuleVO}
     */
    @Override
    public GamePresentRuleVO getPresentDraw(List<GamePresentRuleVO> presentRuleVOS) {
        List<Double> list = presentRuleVOS.stream().map(GamePresentRuleVO :: getPresentOdd).collect(Collectors.toList());
        AliasMethod method = new AliasMethod(list);
        return presentRuleVOS.get(method.next());
    }
}
