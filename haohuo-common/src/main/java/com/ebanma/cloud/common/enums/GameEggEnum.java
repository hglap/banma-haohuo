package com.ebanma.cloud.common.enums;

/**
 * 游戏抽奖蛋枚举
 *
 * @author banma-
 * @date 2023/06/07
 */
public enum GameEggEnum {

    GOLDEN_EGG("金蛋"),
    SILVER_EGG("银蛋"),
    LUCKY_EGG("幸运蛋"),
    ANGEL_EGG("天使蛋"),
    FAKE_EGG("假蛋");

    private String eggType;

    GameEggEnum(String eggType) {
        this.eggType = eggType;
    }

    public static GameEggEnum getGameEgg(String eggType) {
        for (GameEggEnum propEnum : GameEggEnum.values()) {
            if (propEnum.getEggType().equals(eggType) ) {
                return propEnum;
            }
        }
        return null;
    }

    public String getEggType() {
        return eggType;
    }

    public void setEggType(String eggType) {
        this.eggType = eggType;
    }
}

