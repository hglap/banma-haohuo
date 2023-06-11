package com.ebanma.cloud.user.service.impl.prodStrategyImpl;

import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeSearchService;
import com.ebanma.cloud.user.service.ProdStrategy;
import com.ebanma.cloud.user.vo.ProdLifetimeVO;
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
    public ProdLifetimeVO getProdLifeTime(String userId, String principalType, String productCode) {
        ProdLifetime prodLifetime = prodLifetimeSearchService.findProd(userId, "shopping", "SHOP");
        ProdLifetimeVO prodLifeTimeVO = new ProdLifetimeVO();
        if (prodLifetime != null) {
            Long count = prodLifetime.getCount();
            Long rank = (count / 10 + 1) > 6 ? 6 : (count / 10 + 1);
            Long needCount = rank == 6 ? 0 : 10 - count % 10;
            prodLifeTimeVO.setRank(rank);
            prodLifeTimeVO.setCount(count);
            prodLifeTimeVO.setNeedCount(needCount);
        } else {
            prodLifeTimeVO.setRank(1L);
            prodLifeTimeVO.setCount(0L);
            prodLifeTimeVO.setNeedCount(10L);
        }
        return prodLifeTimeVO;
    }
}
