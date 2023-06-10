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
@Component("USER_sign")
public class SignProdStrategy implements ProdStrategy {

    @Resource
    private ProdLifetimeSearchService prodLifetimeSearchService;

    @Override
    public ProdLifeTime getProdLifeTime(String userId, String principalType, String productCode) {
        ProdLifetime prodLifetime = prodLifetimeSearchService.findProd(userId, "sign", "USER");
        ProdLifeTime prodLifeTime = new ProdLifeTime();
        if (prodLifetime != null) {
            Long count = prodLifetime.getCount();
            if (count > 150L) {
                prodLifeTime.setRank(7L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(0L);
            }
            if (count > 100L) {
                prodLifeTime.setRank(6L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(150 - count);
            }
            if (count > 60L) {
                prodLifeTime.setRank(5L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(100 - count);
            }
            if (count > 30L) {
                prodLifeTime.setRank(4L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(60 - count);
            }
            if (count > 10L) {
                prodLifeTime.setRank(3L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(30 - count);
            }
            if (count == 1L) {
                prodLifeTime.setRank(2L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(9L);
            }
            if (count == 0L) {
                prodLifeTime.setRank(1L);
                prodLifeTime.setCount(count);
                prodLifeTime.setNeedCount(1L);
            }
        } else {
            prodLifeTime.setRank(1L);
            prodLifeTime.setCount(0L);
            prodLifeTime.setNeedCount(1L);
        }
        return prodLifeTime;
    }
}
