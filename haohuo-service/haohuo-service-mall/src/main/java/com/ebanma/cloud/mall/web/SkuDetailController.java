package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.service.SkuDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:01
 * @description:
 */
@Validated
@Api(value = "商品详情管理", tags = "商品详情管理")
@RestController
@RequestMapping("/product")
public class SkuDetailController {
    private Logger logger = LoggerFactory.getLogger(SkuDetailController.class);

    @Resource
    private SkuDetailService skuDetailService;


    @ApiOperation(value = "查询宝贝详情", notes = "查询宝贝详情", httpMethod = "GET")
    @GetMapping("/queryDetailById")
    public Result queryDetailById(@NotEmpty String id){
        return ResultGenerator.genSuccessResult(skuDetailService.queryDetailBySkuId(id));
    }
}
