package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.user.dao.ProdLifetimeMapper;
import com.ebanma.cloud.user.dao.UserInfoMapper;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.service.ProdLifetimeSearchService;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import com.ebanma.cloud.user.service.ProdStrategy;
import com.ebanma.cloud.user.vo.ProdLifetimeVO;
import com.ebanma.cloud.user.vo.ShoppingProdLifeTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Slf4j
@Service
@Transactional
public class ProdLifetimeServiceImpl extends AbstractService<com.ebanma.cloud.user.model.ProdLifetime> implements ProdLifetimeService {

    @Resource
    private ProdLifetimeSearchService prodLifetimeSearchService;
    @Resource
    private ProdLifetimeMapper prodLifetimeMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private final Map<String, ProdStrategy> strategyMap = new ConcurrentHashMap<>();

    //通过构造方法将策略注入策略池
    public ProdLifetimeServiceImpl(Map<String, ProdStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }


    /**
     * 查询产品帐详情（购物、签到等等级成长的通用方法）
     *
     * @param userId
     * @param principalType
     * @param productCode
     * @return
     */
    public ProdLifetimeVO getProdLifeTime(String userId, String principalType, String productCode) {
        return strategyMap.get(productCode + "_" + principalType).getProdLifeTime(userId, principalType, productCode);
    }

    /**
     * 购物成长等级查询
     *
     * @param userId
     * @return
     */
    @Override
    public ShoppingProdLifeTime getShoppingProdLifeTime(String userId) {
        ProdLifetimeVO prodLifeTimeVO = strategyMap.get("SHOP_shopping").getProdLifeTime(userId, "shopping", "SHOP");
        ShoppingProdLifeTime shoppingProdLifeTime = new ShoppingProdLifeTime();
        shoppingProdLifeTime.setRank(prodLifeTimeVO.getRank());
        shoppingProdLifeTime.setOrders(prodLifeTimeVO.getCount());
        shoppingProdLifeTime.setNeedOrders(prodLifeTimeVO.getNeedCount());
        return shoppingProdLifeTime;
    }

    /**
     * 产品帐消息处理
     *
     * @param userId
     * @param principalType
     * @param productCode
     * @param amount
     */
    @Override
    public void handleMessage(String userId, String principalType, String productCode, Long amount) {
        com.ebanma.cloud.user.model.ProdLifetime prodLifetime = prodLifetimeSearchService.findProd(userId, principalType, productCode);
        if (prodLifetime == null) {
            //确认userId是否存在
            Boolean isExist = confirmUserById(userId);
            if (!isExist) {
                log.error("用户id{}不存在", userId);
                MallException.fail("该用户不存在");
            }
            prodLifetime = new com.ebanma.cloud.user.model.ProdLifetime();
            prodLifetime.setProductCode(productCode);
            prodLifetime.setPrincipalType(principalType);
            prodLifetime.setPrincipalId(userId);
            if (amount != null) {
                prodLifetime.setAmount(amount);
            }
            prodLifetime.setCount(1L);
            prodLifetime.setCreateTime(new Date());
            prodLifetimeMapper.insert(prodLifetime);
        } else {
            prodLifetime.setCount(prodLifetime.getCount() + 1);
            if (amount != null) {
                prodLifetime.setAmount(prodLifetime.getAmount() + amount);
            }
            prodLifetime.setModifiedTime(new Date());
            prodLifetimeMapper.updateByPrimaryKeySelective(prodLifetime);
        }
    }

    /**
     * 确认用户是否存在
     *
     * @param userId
     * @return
     */
    private Boolean confirmUserById(String userId) {
        Condition condition = new Condition(UserInfo.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<UserInfo> userInfos = userInfoMapper.selectByCondition(condition);
        if (userInfos.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

}
