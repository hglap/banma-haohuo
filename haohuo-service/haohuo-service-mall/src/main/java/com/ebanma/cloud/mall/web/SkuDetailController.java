package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.mall.service.SkuDetailService;
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
}
