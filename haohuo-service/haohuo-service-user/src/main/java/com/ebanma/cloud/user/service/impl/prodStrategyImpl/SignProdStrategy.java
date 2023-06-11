package com.ebanma.cloud.user.service.impl.prodStrategyImpl;

import com.ebanma.cloud.user.service.ProdLifetimeSearchService;
import com.ebanma.cloud.user.service.ProdStrategy;
import com.ebanma.cloud.user.vo.ProdLifetimeVO;
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
    public ProdLifetimeVO getProdLifeTime(String userId, String principalType, String productCode) {
        com.ebanma.cloud.user.model.ProdLifetime prodLifetime = prodLifetimeSearchService.findProd(userId, "sign", "USER");
        ProdLifetimeVO prodLifeTimeVO = new ProdLifetimeVO();
        if (prodLifetime != null) {
            Long count = prodLifetime.getCount();
            if (count > 150L) {
                prodLifeTimeVO.setRank(7L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(0L);
            }
            if (count > 100L) {
                prodLifeTimeVO.setRank(6L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(150 - count);
            }
            if (count > 60L) {
                prodLifeTimeVO.setRank(5L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(100 - count);
            }
            if (count > 30L) {
                prodLifeTimeVO.setRank(4L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(60 - count);
            }
            if (count > 10L) {
                prodLifeTimeVO.setRank(3L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(30 - count);
            }
            if (count == 1L) {
                prodLifeTimeVO.setRank(2L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(9L);
            }
            if (count == 0L) {
                prodLifeTimeVO.setRank(1L);
                prodLifeTimeVO.setCount(count);
                prodLifeTimeVO.setNeedCount(1L);
            }
        } else {
            prodLifeTimeVO.setRank(1L);
            prodLifeTimeVO.setCount(0L);
            prodLifeTimeVO.setNeedCount(1L);
        }
        return prodLifeTimeVO;
    }
}
