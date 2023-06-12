package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.user.model.Address;
import com.ebanma.cloud.user.service.AddressService;
import com.ebanma.cloud.user.utils.InsertGroup;
import com.ebanma.cloud.user.utils.UpdateGroup;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/address/actual")
public class AddressController {
    @Resource
    private AddressService addressService;

    /**
     * 增加地址
     *
     * @param address
     * @return
     */
    @PostMapping("/add")
    public Result add(@Validated(InsertGroup.class) @RequestBody Address address) {
        addressService.saveAddress(address);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        addressService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 修改地址
     *
     * @param address
     * @return
     */
    @PostMapping("/update")
    public Result update(@Validated(UpdateGroup.class) @RequestBody Address address) {
        addressService.updateAddress(address);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Address address = addressService.findById(id);
        return ResultGenerator.genSuccessResult(address);
    }

    /**
     * 查询我的地址列表
     *
     * @param page
     * @param size
     * @param userId
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size , String userId) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(Address.class);
        condition.createCriteria().andEqualTo("userId", userId);
        List<Address> list = addressService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
