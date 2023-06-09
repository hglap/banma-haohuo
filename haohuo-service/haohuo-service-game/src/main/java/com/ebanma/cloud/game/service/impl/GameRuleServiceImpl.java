package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.enums.GameEggEnum;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.GameRedisEnum;
import com.ebanma.cloud.game.dao.GameRuleMapper;
import com.ebanma.cloud.game.model.po.GameRule;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.vo.GameDrawVO;
import com.ebanma.cloud.game.model.vo.GameEggRuleVO;
import com.ebanma.cloud.game.model.vo.GamePresentRuleVO;
import com.ebanma.cloud.game.service.GameRuleService;
import com.ebanma.cloud.game.service.GameUserPropService;
import com.ebanma.cloud.game.util.AliasMethod;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class  GameRuleServiceImpl extends AbstractService<GameRule> implements GameRuleService {

    private static final int  REMAIN_TIMES = 100;

    @Autowired
    private RedissonClient redissonClient;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private GameRuleMapper gameRuleMapper;


    @Resource
    private GameUserPropService gameUserPropService;

    @Override
    public List<GameEggRuleVO> getGameRules(GameUserInfo userInfo, int propCode) {
        List<GameEggRuleVO> gameRules = this.getGameRules();
        //如果使用道具
        //获取使用道具类型枚举
        GamePriceOrPropEnum userProp=GamePriceOrPropEnum.getGamePropByValue(propCode);
        if ( userProp != null && !userProp.equals(GamePriceOrPropEnum.NULL) ) {
            userInfo.getGameUserProp().forEach( m ->{
                //如果道具匹配 且 道具大于0
                if (userProp.getValue()-m.getPropCode()==0 && m.getPropRemainCount() > 0) {
                    //道具数量减一
                    m.setPropRemainCount(m.getPropRemainCount() - 1);
                    //记录用户使用的道具,方便后续更新数据库
                    userInfo.setUseGameUserProp(m);
                    //更新数据库
                    gameUserPropService.update(m);
                    //使用道具类型判断
                    switch (Objects.requireNonNull(GamePriceOrPropEnum.getGamePropByValue(propCode))) {
                        case A:
                            usePropA(gameRules);
                            break;
                        case B:
                            usePropB(gameRules);
                            break;
                        case C:
                            usePropC(gameRules);
                            break;
                    }
                }

            });
        }

        return gameRules;
    }

    /**
     * 保底抽奖
     *
     * @param gameRules 游戏规则
     * @return {@link GameEggRuleVO}
     */
    @Override
    public GameEggRuleVO getEggDrawByGuaranteed(List<GameEggRuleVO> gameRules) {
        //1.上锁
        GameEggRuleVO gameEggRuleVO = null;
        RLock lock = redissonClient.getLock(GameRedisEnum.DRAW_LOCK.getKey());
        try {
            //2.获取抽奖次数
            GameDrawVO gameDrawVO = (GameDrawVO) redisTemplate.opsForValue().get(GameRedisEnum.DRAW_INFO);
            //2.1如果没有信息，新建对象
            if (gameDrawVO == null) {
                //获取金蛋概率
                for(GameEggRuleVO m :gameRules){
                    if (m.getEggType().equals(GameEggEnum.GOLDEN_EGG.getEggType())) {
                        gameDrawVO = new GameDrawVO(REMAIN_TIMES,m.getEggOdd());
                        redisTemplate.opsForValue().set(GameRedisEnum.DRAW_INFO,gameDrawVO);
                    }
                }
            }
            //断言不为空
            assert gameDrawVO != null :"金蛋保底错误";

            //3.保底判断
            if( gameDrawVO.getGuaranteedTimes()-gameDrawVO.getWinning() ==0){
                //3.1 如果金蛋次数==保底要求次数
                //金蛋个数以满足,更新概率

                //抽奖
                gameEggRuleVO = getEggDraw(gameRules);

            }else if(gameDrawVO.getGuaranteedTimes() - gameDrawVO.getWinning() == gameDrawVO.getRemainTimes()){

                //3.2如果还需要出的金蛋个人 == 剩余抽奖次数,则必出金蛋
                for( GameEggRuleVO m :gameRules){
                    //如果为金蛋
                    if (GameEggEnum.GOLDEN_EGG.getEggType().equals(m.getEggType())) {
                        gameEggRuleVO = m;
                        break;
                    }
                }
            }else {
                //正常抽奖
                gameEggRuleVO = getEggDraw(gameRules);
            }

            //4.抽奖次数更新
            if ( gameDrawVO.getGuaranteedTimes() > 1) {
                //4.1.1如果还有剩余次数进行次数-1;
                gameDrawVO.setRemainTimes(gameDrawVO.getGuaranteedTimes() - 1);
                //4.1.2 金蛋则win+1
                if (GameEggEnum.GOLDEN_EGG.getEggType().equals(gameEggRuleVO.getEggType())) {
                    gameDrawVO.setWinning(gameDrawVO.getWinning() + 1);
                }
            }else if( gameDrawVO.getGuaranteedTimes() == 1 ) {
                //4.2 如果已经为1,则重置次数
                gameDrawVO.resetting();
            }
            //更新redis
            redisTemplate.opsForValue().set(GameRedisEnum.DRAW_INFO, gameDrawVO);


        }catch (Exception e) {
            throw e;
        }finally {
            if(lock != null && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return gameEggRuleVO;
    }

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
        List<GameEggRuleVO> gameEggRuleVOS = (List<GameEggRuleVO>) redisTemplate.opsForValue().get(GameRedisEnum.GAME_RULE.getKey());
        //2.判断redis里是否存在,如果没有进行数据库查询
        if( gameEggRuleVOS == null ){
            //2.1查询
            Map<String, List<GameRule>> map = this.findAll().stream().collect(Collectors.groupingBy(GameRule::getEggType));
            //2.2数据处理
            List<GameEggRuleVO> newGameEggRuleVOS = new ArrayList<>();
            map.values().forEach( (value) -> {
                newGameEggRuleVOS.add( new GameEggRuleVO(value) );
            });
            //2.3储存进Redis
            if ( !CollectionUtils.isEmpty(newGameEggRuleVOS) ) {
                redisTemplate.opsForValue().set( GameRedisEnum.GAME_RULE.getKey(),newGameEggRuleVOS);
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


    /**
     * 使用道具-列奥德罗的愤怒
     * 列奥德罗是风暴之主，可以主动降下一道闪电，打碎一枚假蛋。
     * 此次砸蛋动作内假蛋概率减少10%，银蛋概率增加10%。
     *
     * @param ruleVOS 规则vos
     */
    private  void usePropA(List<GameEggRuleVO> ruleVOS ){
        ruleVOS.forEach( m -> {
            //如果银蛋,概率增加10%
            if (GameEggEnum.SILVER_EGG.getEggType().equals(m.getEggType())) {
                m.setEggOdd( m.getEggOdd()+0.1d);
            }
            //如果为假蛋,概率减少10%
            if (GameEggEnum.FAKE_EGG.getEggType().equals(m.getEggType())) {
                m.setEggOdd( m.getEggOdd() - 0.1d);
            }
        });


    }

    /**
     * 使用道具-阿西曼尼斯的幸运
     * 砸蛋游戏内的道具：阿西曼尼斯是黑夜女神，可以掌管好运与厄运，
     * 使用祝福后此次砸蛋动作内，如果砸中金蛋必然是5元无门槛红包，如果砸中银蛋必然是50积分奖励。
     *
     * @param ruleVOS 规则vos
     */
    private  void usePropB(List<GameEggRuleVO> ruleVOS ){
        ruleVOS.forEach( m -> {
            //如果金蛋,必然是5元红包
            if (GameEggEnum.GOLDEN_EGG.getEggType().equals(m.getEggType())) {
                m.getPresentRuleVOS().clear();
                m.getPresentRuleVOS().add(new GamePresentRuleVO(GamePriceOrPropEnum.RED_PACKET.getKey(),5,1d));
            }
            //如果为银蛋,必然为50积分
            if (GameEggEnum.SILVER_EGG.getEggType().equals(m.getEggType())) {
                m.getPresentRuleVOS().clear();
                m.getPresentRuleVOS().add(new GamePresentRuleVO(GamePriceOrPropEnum.POINT.getKey(),50,1d));
            }
        });
    }

    /**
     * 使用道具-奥赛库斯的正义
     * 奥赛库斯是契约之神，他的正义会让玩家受到保护。
     * 使用后此次砸蛋动作内，幸运锤概率减少10%，天使蛋概率增加10%。
     *
     * @param ruleVOS 规则vos
     */
    private  void usePropC(List<GameEggRuleVO> ruleVOS ){
        ruleVOS.forEach( m -> {
            //如果幸运锤,概率-10%
            if (GameEggEnum.LUCKY_EGG.getEggType().equals(m.getEggType())) {
                m.setEggOdd( m.getEggOdd() - 0.1d);
            }
            //如果为天使蛋,概率增加10%
            if (GameEggEnum.ANGEL_EGG.getEggType().equals(m.getEggType())) {
                m.setEggOdd( m.getEggOdd() + 0.1d);
            }
        });
    }


}
