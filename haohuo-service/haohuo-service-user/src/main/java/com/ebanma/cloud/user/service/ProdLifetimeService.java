package com.ebanma.cloud.user.service;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.user.vo.ProdLifetimeVO;
import com.ebanma.cloud.user.vo.ShoppingProdLifeTime;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface ProdLifetimeService extends Service<com.ebanma.cloud.user.model.ProdLifetime> {

    ShoppingProdLifeTime getShoppingProdLifeTime(String userId);

    void handleMessage(String userId, String principalType, String productCode, Long amount);

    ProdLifetimeVO getProdLifeTime(String userId, String principalType, String productCode);
}
