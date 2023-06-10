package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.user.dao.ProdLifetimeMapper;
import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 鹿胜宝
 * @version $ Id: ProdLifetimeSearchServiceImpl, v 0.1 2023/06/10 22:47 banma-0193 Exp $
 */
@Slf4j
@Service
public class ProdLifetimeSearchServiceImpl implements ProdLifetimeSearchService {
    @Resource
    private ProdLifetimeMapper prodLifetimeMapper;

    /**
     * 根据查询产品、主体类型、主体id查询产品帐
     *
     * @param userId
     * @param principalType
     * @param productCode
     * @return
     */
    public ProdLifetime findProd(String userId, String principalType, String productCode) {
        Condition condition = new Condition(ProdLifetime.class);
        condition.createCriteria().andEqualTo("principalId", userId)
                .andEqualTo("principalType", principalType)
                .andEqualTo("productCode", productCode);
        List<ProdLifetime> prodLifetimes = prodLifetimeMapper.selectByCondition(condition);
        ProdLifetime prodLifetime;
        if (prodLifetimes.size() > 1) {
            log.error("同一产品下该用户存在多个产品帐，产品类型为{}，主体类型为{}，用户id为{}", productCode, principalType, userId);
            MallException.fail("产品帐内部错误，同一产品下该用户存在多个产品帐");
        }
        if (prodLifetimes.size() == 1) {
            prodLifetime = prodLifetimes.get(0);
        } else {
            return null;
        }
        return prodLifetime;
    }
}
