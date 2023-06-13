package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuRecordInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.service.SkuRecordService;
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
@Api(value = "商品记录管理", tags = "商品记录管理")
@RestController
@RequestMapping("/record")
public class SkuRecordController {
    private Logger logger = LoggerFactory.getLogger(SkuRecordController.class);

    @Resource
    private SkuRecordService skuRecordService;

    @ApiOperation(value = "根据类型和商品ID获取对应记录数量", notes = "根据类型和商品ID获取对应记录数量", httpMethod = "POST")
    @PostMapping("/getRecrodCountBySkuIdAndType")
    public Result<Integer> getRecrodCountBySkuIdAndType(@RequestBody @NotNull(message = "搜索参数不能为空") SkuRecordSearchDTO skuRecordSearchDTO){
        return ResultGenerator.genSuccessResult(skuRecordService.getRecrodCountBySkuIdAndType(skuRecordSearchDTO));
    }

    @ApiOperation(value = "根据类型、商品ID、用户ID获取对应记录数量", notes = "根据类型和商品ID获取对应记录数量", httpMethod = "POST")
    @PostMapping("/getRecrodCountBySkuIdAndTypeAndUserId")
    public Result<Integer> getCollectCount(@RequestBody @NotNull(message = "搜索参数不能为空") SkuRecordSearchDTO skuRecordSearchDTO){
        return ResultGenerator.genSuccessResult(skuRecordService.getRecrodCountBySkuIdAndTypeAndUserId(skuRecordSearchDTO));
    }

    @ApiOperation(value = "商品一键收藏", notes = "商品一键收藏", httpMethod = "GET")
    @GetMapping("/collect")
    public Result<Boolean> getCollectCount(@NotEmpty(message = "商品ID不能为空") String productId){
        return ResultGenerator.genSuccessResult(skuRecordService.collect(productId));
    }

    @ApiOperation(value = "插入记录", notes = "插入记录", httpMethod = "POST")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody @NotEmpty(message = "记录信息不能为空") SkuRecordInsertDTO recordInsertDTO){
        return ResultGenerator.genSuccessResult(skuRecordService.add(recordInsertDTO));
    }


}
