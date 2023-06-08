package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoSearchDTO;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:00
 * @description:
 */
@Validated
@Api(value = "商品信息管理", tags = "商品信息管理")
@RestController
@RequestMapping("/product")
public class SkuInfoController {
    private Logger logger = LoggerFactory.getLogger(SkuInfoController.class);

    @Resource
    private SkuInfoService skuInfoService;

    @ApiOperation(value = "分页查询商品信息", notes = "【APP】分页查询商品信息", httpMethod = "POST")
    @PostMapping("/queryList")
    public Result<PageInfo> queryList(@RequestBody SkuInfoSearchDTO skuInfoSearchDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.queryList(skuInfoSearchDTO));
    }

    @ApiOperation(value = "查询商品详情", notes = "【APP】查询商品详情", httpMethod = "GET")
    @GetMapping("/queryById")
    public Result queryById(String id){
        return ResultGenerator.genSuccessResult(skuInfoService.queryById(id));
    }

    @ApiOperation(value = "获取商品推荐精选", notes = "【APP】获取商品推荐精选", httpMethod = "POST")
    @PostMapping("/queryRecommendList")
    public Result queryRecommendList(@RequestBody SkuInfoSearchDTO skuInfoSearchDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.queryRecommendList(skuInfoSearchDTO));
    }

    @ApiOperation(value = "商品列表分页查询", notes = "【服务端】商品列表分页查询", httpMethod = "POST")
    @PostMapping("/searchList")
    public Result<PageInfo> searchList(@RequestBody SkuInfoSearchDTO skuInfoSearchDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.searchList(skuInfoSearchDTO));
    }

    @ApiOperation(value = "商品信息新增", notes = "【服务端】商品信息新增", httpMethod = "POST")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SkuInfoInsertDTO skuInfoInsertDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.add(skuInfoInsertDTO));
    }

    @ApiOperation(value = "商品信息编辑", notes = "【服务端】商品信息编辑", httpMethod = "POST")
    @PostMapping("/edit")
    public Result<Boolean> edit(@RequestBody SkuInfoEditDTO skuInfoEditDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.edit(skuInfoEditDTO));
    }

    @ApiOperation(value = "商品上下架", notes = "【服务端】商品上下架", httpMethod = "GET")
    @GetMapping("/editStatus")
    public Result<Boolean> editStatus(@RequestParam("id")  String id , @RequestParam("useStatus")String useStatus){
        return ResultGenerator.genSuccessResult(skuInfoService.editStatus(id,useStatus));
    }

    @ApiOperation(value = "商品删除", notes = "【服务端】商品删除", httpMethod = "GET")
    @GetMapping("/del")
    public Result<Boolean> del(@RequestParam("id")  String id ){
        return ResultGenerator.genSuccessResult(skuInfoService.del(id));
    }

}
