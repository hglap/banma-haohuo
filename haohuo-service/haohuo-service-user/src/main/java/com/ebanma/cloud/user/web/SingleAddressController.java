package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.user.model.Address;
import com.ebanma.cloud.user.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/address")
public class SingleAddressController {
    @Resource
    private AddressService addressService;

    /**
     * 增加及修改地址
     *
     * @param address
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.addAndUpdate(address);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam String userId) {
        addressService.deleteByUserId(userId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result<Address> detail(@RequestParam String userId) {
        Address address = addressService.findByUserId(userId);
        return ResultGenerator.genSuccessResult(address);
    }

}
