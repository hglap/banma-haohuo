package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.trans.api.dto.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.api.dto.TransAccountLogVO;
import com.ebanma.cloud.trans.api.openfeign.TransFeign;
import com.ebanma.cloud.user.dao.UserInfoMapper;
import com.ebanma.cloud.user.model.Address;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.service.AddressService;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import com.ebanma.cloud.user.service.UserInfoService;
import com.ebanma.cloud.user.vo.ProdLifetimeVO;
import com.ebanma.cloud.user.vo.UserInfoVO;
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
public class UserInfoServiceImpl extends AbstractService<UserInfo> implements UserInfoService {
    @Resource
    private ProdLifetimeService prodLifetimeService;
    @Resource
    private AddressService addressService;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private TransFeign transFeign;

    @Override
    public UserInfoVO getUserInfo(String userId) {
        UserInfoVO userInfoVO = new UserInfoVO();
        Condition condition = new Condition(UserInfo.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<UserInfo> userInfos = userInfoMapper.selectByCondition(condition);
        if (userInfos.size() != 1) {
            MallException.fail("用户信息异常，存在重复的用户账号");
        }
        UserInfo userInfo = userInfos.get(0);
        //设置头像、昵称
        userInfoVO.setAvator(userInfo.getHeadImg());
        userInfoVO.setNickname(userInfo.getNickName());
        //购物等级相关,等级
        ProdLifetimeVO shoppingProdLifeTime = prodLifetimeService.getProdLifeTime(userId, "shopping", "SHOP");
        userInfoVO.setShoppingLevel(String.valueOf(shoppingProdLifeTime.getRank()));
        //成长等级相关
        ProdLifetimeVO growProdLifeTime = prodLifetimeService.getProdLifeTime(userId, "sign", "USER");
        userInfoVO.setGrowLevel(String.valueOf(growProdLifeTime.getRank()));
        userInfoVO.setDayToUp(String.valueOf(growProdLifeTime.getNeedCount()));
        //总积分,feign调用
        TransAccountLogSearchVO searchVO = new TransAccountLogSearchVO();
        searchVO.setUserId(userId);
        searchVO.setBizType(0);
        Result<TransAccountLogVO> transInfo = transFeign.getTransInfo(searchVO);
        if (transInfo.getCode() == 200 && transInfo.getData() != null) {
            TransAccountLogVO data = transInfo.getData();
            userInfoVO.setPoints(String.valueOf(data.getAmountPoints()));
        }
        //地址相关
        Condition addressCondition = new Condition(Address.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<Address> list = addressService.findByCondition(addressCondition);
        userInfoVO.setAddressList(list);
        return userInfoVO;
    }
}
