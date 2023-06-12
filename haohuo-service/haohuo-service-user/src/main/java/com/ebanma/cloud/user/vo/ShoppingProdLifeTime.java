package com.ebanma.cloud.user.vo;

/**
 * @author 鹿胜宝
 * @version $ Id: ShoppingProdLifeTime, v 0.1 2023/06/10 18:08 banma-0193 Exp $
 */

public class ShoppingProdLifeTime {
    //购物等级
    private Long rank = 1L;
    //已下单量
    private Long orders = 0L;
    //升级还需的下单量
    private Long needOrders = 10L;

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getOrders() {
        return orders;
    }

    public void setOrders(Long orders) {
        this.orders = orders;
    }

    public Long getNeedOrders() {
        return needOrders;
    }

    public void setNeedOrders(Long needOrders) {
        this.needOrders = needOrders;
    }
}
