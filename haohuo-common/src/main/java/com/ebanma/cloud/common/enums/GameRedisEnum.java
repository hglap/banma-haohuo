package com.ebanma.cloud.common.enums;

/**
 * 游戏域reids的key值枚举
 *
 * @author banma-
 * @date 2023/06/07
 */
public enum GameRedisEnum {

    /**
     * 用户锁
     */
    USER_LOCK("game:u:l:"),
    /**
     * 用户信息
     */
    USER_INFO("game:u:i:"),
    /**
     * 用户信息保留时间
     */
    USER_INFO_TIME("-1"),
    /**
     * 用户购买锁
     */
    USER_BUY("game:u:buy:"),
    /**
     * 抽奖锁
     */
    DRAW_LOCK("game:draw:l"),
    /**
     *抽奖次数
     */
    DRAW_TOTAL("game:draw:total"),
    DRAW_WINING("game:draw:wining"),
    /**
     * 抽奖信息[概率控制]
     */
    DRAW_INFO("game:draw:i"),

    /**
     * 游戏规则
     */
    GAME_RULE("game:rule");
    private String key;

    GameRedisEnum(String key) {
        this.key = key;
    }

    public static GameRedisEnum getRedisKey(String key) {
        for (GameRedisEnum redisEnum : GameRedisEnum.values()) {
            if (redisEnum.getKey().equals(key) ) {
                return redisEnum;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

