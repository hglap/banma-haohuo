package com.ebanma.cloud.user.service;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.vo.ShoppingProdLifeTime;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface ProdLifetimeService extends Service<ProdLifetime> {

    ShoppingProdLifeTime getShoppingProdLifeTime(String userId);
}
