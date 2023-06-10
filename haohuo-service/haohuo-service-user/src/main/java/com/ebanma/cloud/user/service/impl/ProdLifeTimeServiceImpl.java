package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.user.dao.ProdLifetimeMapper;
import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import com.ebanma.cloud.user.vo.ShoppingProdLifeTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class ProdLifeTimeServiceImpl extends AbstractService<ProdLifetime> implements ProdLifetimeService {
    @Resource
    private ProdLifetimeMapper prodLifetimeMapper;

    @Override
    public ShoppingProdLifeTime getShoppingProdLifeTime(String userId) {
        Condition condition = new Condition(ProdLifetime.class);
        condition.createCriteria().andEqualTo("principalId", userId)
                .andEqualTo("principalType", "shopping")
                .andEqualTo("productCode", "SHOP");
        List<ProdLifetime> prodLifetimes = prodLifetimeMapper.selectByCondition(condition);
        if (prodLifetimes.size() > 1) {
            MallException.fail("产品帐内部错误，该用户存在多个产品帐");
        }
        ShoppingProdLifeTime shoppingProdLifeTime = new ShoppingProdLifeTime();
        if (prodLifetimes.size() == 1) {
            ProdLifetime prodLifetime = prodLifetimes.get(0);
            //消费次数转换等级
            Long count = prodLifetime.getCount();
            Long rank = (count / 10 + 1) > 6 ? 6 : (count / 10 + 1);
            Long needOrders = rank == 6 ? 0 : 10 - count % 10;
            shoppingProdLifeTime.setOrders(count);
            shoppingProdLifeTime.setRank(rank);
            shoppingProdLifeTime.setNeedOrders(needOrders);
        }
        return shoppingProdLifeTime;
    }
}
