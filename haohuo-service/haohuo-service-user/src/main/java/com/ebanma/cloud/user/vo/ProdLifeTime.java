package com.ebanma.cloud.user.vo;

/**
 * @author 鹿胜宝
 * @version $ Id: ShoppingProdLifeTime, v 0.1 2023/06/10 18:08 banma-0193 Exp $
 */

public class ProdLifeTime {
    //等级
    private Long rank;
    //当前量
    private Long count;
    //升级还需的量
    private Long needCount;
    //累计额度
    private Long amount;
    //升级还需的量
    private Long needAmount;

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getNeedCount() {
        return needCount;
    }

    public void setNeedCount(Long needCount) {
        this.needCount = needCount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getNeedAmount() {
        return needAmount;
    }

    public void setNeedAmount(Long needAmount) {
        this.needAmount = needAmount;
    }
}
