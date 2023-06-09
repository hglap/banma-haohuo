package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.user.dao.AddressMapper;
import com.ebanma.cloud.user.model.Address;
import com.ebanma.cloud.user.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class AddressServiceImpl extends AbstractService<Address> implements AddressService {
    @Resource
    private AddressMapper addressMapper;

    @Override
    public void saveAddress(Address address) {
        //如果没有填写是否默认，默认为否
        if (StringUtils.isBlank(address.getIsDefault())) {
            address.setIsDefault("0");
        }
        //如果添加地址前没有默认地址，则该地址为默认地址
        String userId = address.getUserId();
        Condition condition = new Condition(Address.class);
        condition.createCriteria().andEqualTo("isDefault", "1");
        List<Address> addresses = addressMapper.selectByCondition(condition);
        if (addresses.size() == 0) {
            address.setIsDefault("1");
        } else {
            //如果之前有默认地址，且此次设置默认为地址，需要改变之前的默认地址。
            Address defaultAddress = addresses.get(0);
            if (Objects.equals(address.getIsDefault(), "1")) {
                defaultAddress.setIsDefault("0");
                addressMapper.updateByPrimaryKey(defaultAddress);
            }
        }
        addressMapper.insertSelective(address);
    }

    @Override
    public void updateAddress(Address address) {
        //如果修改为设置默认，需要将存在的默认设为否
        if (Objects.equals(address.getIsDefault(), "1")) {
            Condition condition = new Condition(Address.class);
            condition.createCriteria().andEqualTo("isDefault", "1");
            List<Address> addresses = addressMapper.selectByCondition(condition);
            if (addresses.size() != 0) {
                Address defaultAddress = addresses.get(0);
                defaultAddress.setIsDefault("0");
                addressMapper.updateByPrimaryKey(defaultAddress);
            }
        }
        addressMapper.updateByPrimaryKeySelective(address);
    }
}
