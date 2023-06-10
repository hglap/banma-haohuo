package com.ebanma.cloud.user.service.impl.prodStrategyImpl;

import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeSearchService;
import com.ebanma.cloud.user.service.ProdStrategy;
import com.ebanma.cloud.user.vo.ProdLifeTime;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 鹿胜宝
 * @version $ Id: ShoppingProdLifeTime, v 0.1 2023/06/10 21:32 banma-0193 Exp $
 */
@Component("SHOP_shopping")
public class ShoppingProdStrategy implements ProdStrategy {

    @Resource
    private ProdLifetimeSearchService prodLifetimeSearchService;

    @Override
    public ProdLifeTime getProdLifeTime(String userId, String principalType, String productCode) {
        ProdLifetime prodLifetime = prodLifetimeSearchService.findProd(userId, "shopping", "SHOP");
        ProdLifeTime prodLifeTime = new ProdLifeTime();
        if (prodLifetime != null) {
            Long count = prodLifetime.getCount();
            Long rank = (count / 10 + 1) > 6 ? 6 : (count / 10 + 1);
            Long needCount = rank == 6 ? 0 : 10 - count % 10;
            prodLifeTime.setRank(rank);
            prodLifeTime.setCount(count);
            prodLifeTime.setNeedCount(needCount);
        } else {
            prodLifeTime.setRank(1L);
            prodLifeTime.setCount(0L);
            prodLifeTime.setNeedCount(10L);
        }
        return prodLifeTime;
    }
}
