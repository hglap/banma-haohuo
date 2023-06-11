package com.ebanma.cloud.user.service;

import com.ebanma.cloud.user.vo.ProdLifetimeVO;

/**
 * @author 鹿胜宝
 * @version $ Id: ProdStrategy, v 0.1 2023/06/10 21:43 banma-0193 Exp $
 */
public interface ProdStrategy {
    ProdLifetimeVO getProdLifeTime(String userId, String principalType, String productCode);
}
