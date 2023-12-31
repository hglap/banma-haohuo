package com.ebanma.cloud.user.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.user.model.Address;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface AddressService extends Service<Address> {

    void saveAddress(Address address);

    void updateAddress(Address address);

    void addAndUpdate(Address address);

    void deleteByUserId(String userId);

    Address findByUserId(String userId);
}
