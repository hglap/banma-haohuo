package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import com.ebanma.cloud.user.vo.ProdLifeTime;
import com.ebanma.cloud.user.vo.ShoppingProdLifeTime;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/prod/lifetime")
public class ProdLifetimeController {
    @Resource
    private ProdLifetimeService prodLifetimeService;

    @PostMapping("/add")
    public Result add(@RequestBody ProdLifetime prodLifetime) {
        prodLifetimeService.save(prodLifetime);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        prodLifetimeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ProdLifetime prodLifetime) {
        prodLifetimeService.update(prodLifetime);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ProdLifetime prodLifetime = prodLifetimeService.findById(id);
        return ResultGenerator.genSuccessResult(prodLifetime);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ProdLifetime> list = prodLifetimeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 购物成长等级查询的接口
     *
     * @param userId
     * @return
     */
    @PostMapping("/getShoppingLevel")
    public Result ShoppingLevel(@RequestParam String userId) {
        ShoppingProdLifeTime shoppingProdLifeTime = prodLifetimeService.getShoppingProdLifeTime(userId);
        return ResultGenerator.genSuccessResult(shoppingProdLifeTime);
    }

    /**
     * 产品帐（登录、购物等级详情数据）查询通用接口
     *
     * @param userId
     * @param principalType
     * @param productCode
     * @return
     */
    @PostMapping("/getProdDetail")
    public Result ShoppingLevel(@RequestParam String userId, String principalType, String productCode) {
        ProdLifeTime prodLifeTime = prodLifetimeService.getProdLifeTime(userId, principalType, productCode);
        return ResultGenerator.genSuccessResult(prodLifeTime);
    }
}
