package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
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

    @ApiOperation(value = "分页查询商品信息", notes = "分页查询商品信息", httpMethod = "POST")
    @PostMapping("/queryList")
    public Result<PageInfo> queryList(@RequestBody SkuInfoSearchDTO skuInfoSearchDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.queryList(skuInfoSearchDTO));
    }

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/queryById")
    public Result queryById(String id){
        return ResultGenerator.genSuccessResult(skuInfoService.queryById(id));
    }

    @ApiOperation(value = "获取商品推荐精选", notes = "获取商品推荐精选", httpMethod = "POST")
    @PostMapping("/queryRecommendList")
    public Result queryRecommendList(@RequestBody SkuInfoSearchDTO skuInfoSearchDTO){
        return null;
//        return ResultGenerator.genSuccessResult(skuInfoService.queryRecommendList(skuInfoSearchDTO));
    }

}
