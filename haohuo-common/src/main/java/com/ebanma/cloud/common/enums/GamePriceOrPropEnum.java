package com.ebanma.cloud.common.enums;

/**
 * 游戏奖励或道具枚举
 * 道具来源于奖励,所以进行归一处理
 * [和账户平台接口保持一致]
 * 积分-0
 * 红包-1
 * @author banma-
 * @date 2023/06/07
 */
public enum GamePriceOrPropEnum {


    A("列奥德罗的愤怒",11),
    B("阿西曼尼斯的幸运",12),
    C("奥赛库斯的正义",13),
    POINT("积分",0),
    RED_PACKET("红包",1),
    LUCKY_HAMMER("幸运锤",20),
    ANGEL_BLESSINGS("天使祝福",21),
    NULL("谢谢惠顾",10);

    private String key;
    private int value;

    GamePriceOrPropEnum(String key, int value) {
        this.key = key;
        this.value = value;
    }


    /**
     * 获得游戏道具
     * 判断使用的道具类型
     *
     * @param value 价值
     * @return {@link GamePriceOrPropEnum}
     */
    public static GamePriceOrPropEnum getGamePropByValue(int value) {
        for (GamePriceOrPropEnum propEnum : GamePriceOrPropEnum.values()) {
            if (propEnum.getValue()-value ==0 ) {
                return propEnum;
            }
        }
        return null;
    }

    public static GamePriceOrPropEnum getGamePropByKey(String key) {
        for (GamePriceOrPropEnum propEnum : GamePriceOrPropEnum.values()) {
            if (propEnum.getKey().equals(key) ) {
                return propEnum;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

