package com.ebanma.cloud.common.enums;

/**
 * 游戏域reids的key值枚举
 *
 * @author banma-
 * @date 2023/06/07
 */
public enum GameRedisEnum {

    USER_LOCK("game:u:l:"),
    DRAW_LOCK("game:draw:l"),
    DRAW_INFO("game:draw:i"),
    USER_INFO("game:u:i:"),
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

