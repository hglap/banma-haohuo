package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuInventoryInsertDTO;
import com.ebanma.cloud.mall.service.SkuInventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:03
 * @description:
 */
@Validated
@Api(value = "商品库存管理", tags = "商品库存管理")
@RestController
@RequestMapping("/inventory")
public class SkuInventoryController {
    private Logger logger = LoggerFactory.getLogger(SkuInventoryController.class);

    @Resource
    private SkuInventoryService skuInventoryService;

    @ApiOperation(value = "库存信息新增", notes = "库存信息新增", httpMethod = "POST")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SkuInventoryInsertDTO skuInventoryInsertDTO){
        return ResultGenerator.genSuccessResult(skuInventoryService.add(skuInventoryInsertDTO));
    }

}
