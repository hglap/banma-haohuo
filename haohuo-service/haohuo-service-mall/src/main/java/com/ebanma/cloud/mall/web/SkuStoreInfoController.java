package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoSearchDTO;
import com.ebanma.cloud.mall.service.SkuStoreInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:04
 * @description:
 */
@Validated
@Api(value = "商家信息管理", tags = "商家信息管理")
@RestController
@RequestMapping("/store")
public class SkuStoreInfoController {
    private Logger logger = LoggerFactory.getLogger(SkuStoreInfoController.class);

    @Resource
    private SkuStoreInfoService skuStoreInfoService;

    @ApiOperation(value = "分页查询商家列表", notes = "【服务端】分页查询商家列表", httpMethod = "POST")
    @PostMapping("/searchList")
    public Result<PageInfo> searchList(@RequestBody SkuStoreInfoSearchDTO skuStoreInfoSearchDTO){
        return ResultGenerator.genSuccessResult(skuStoreInfoService.searchList(skuStoreInfoSearchDTO));
    }

    @ApiOperation(value = "商家新增", notes = "【服务端】商家新增", httpMethod = "POST")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody  @NotNull(message = "商家新增信息不能为空") SkuStoreInfoInsertDTO skuStoreInfoInsertDTO){
        return ResultGenerator.genSuccessResult(skuStoreInfoService.add(skuStoreInfoInsertDTO));
    }

    @ApiOperation(value = "商家编辑", notes = "【服务端】商家编辑", httpMethod = "POST")
    @PostMapping("/edit")
    public Result<Boolean> edit(@RequestBody @NotNull(message = "商家编辑信息不能为空") SkuStoreInfoEditDTO skuStoreInfoEditDTO){
        return ResultGenerator.genSuccessResult(skuStoreInfoService.edit(skuStoreInfoEditDTO));
    }

    @ApiOperation(value = "商家启用", notes = "【服务端】商家启用", httpMethod = "GET")
    @GetMapping("/editStatus")
    public Result<Boolean> editStatus(@RequestParam("id") @NotEmpty(message = "商家ID不能为空") String id , @RequestParam("useStatus")@NotEmpty(message = "商家状态不能为空") String useStatus){
        return ResultGenerator.genSuccessResult(skuStoreInfoService.editStatus(id,useStatus));
    }
    @ApiOperation(value = "商家删除", notes = "【服务端】商家删除", httpMethod = "GET")
    @GetMapping("/del")
    public Result<Boolean> del(@RequestParam("id") @NotEmpty(message = "商家ID不能为空") String id ){
        return ResultGenerator.genSuccessResult(skuStoreInfoService.del(id));
    }





}
