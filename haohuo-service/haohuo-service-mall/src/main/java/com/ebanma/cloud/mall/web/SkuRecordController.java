package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.service.SkuRecordService;
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
    public Result<Integer> getRecrodCountBySkuIdAndType(@RequestBody SkuRecordSearchDTO skuRecordSearchDTO){
        return ResultGenerator.genSuccessResult(skuRecordService.getRecrodCountBySkuIdAndType(skuRecordSearchDTO));
    }

    @ApiOperation(value = "根据类型、商品ID、用户ID获取对应记录数量", notes = "根据类型和商品ID获取对应记录数量", httpMethod = "POST")
    @PostMapping("/getRecrodCountBySkuIdAndTypeAndUserId")
    public Result<Integer> getCollectCount(@RequestBody SkuRecordSearchDTO skuRecordSearchDTO){
        return ResultGenerator.genSuccessResult(skuRecordService.getRecrodCountBySkuIdAndTypeAndUserId(skuRecordSearchDTO));
    }


}
