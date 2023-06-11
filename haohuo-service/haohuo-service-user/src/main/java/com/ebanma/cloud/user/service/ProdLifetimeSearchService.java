package com.ebanma.cloud.user.service;

import com.ebanma.cloud.user.model.ProdLifetime;

/**
 * @author 鹿胜宝
 * @version $ Id: ProdLifetimeSearchService, v 0.1 2023/06/10 22:47 banma-0193 Exp $
 */
public interface ProdLifetimeSearchService {
    ProdLifetime findProd(String userId, String principalType, String productCode);
}
