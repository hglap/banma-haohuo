package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.service.SkuStoreInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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



}
