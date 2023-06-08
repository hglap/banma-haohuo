package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.mall.model.po.SkuCategoryPO;
import com.ebanma.cloud.mall.service.SkuCategoryService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: why
 * @date: 2023/6/6
 * @time: 17:41
 * @description:
 */
@Validated
@Api(value = "商品分类管理", tags = "商品分类管理")
@RestController
@RequestMapping("/category")
public class SkuCategoryController {
    private Logger logger = LoggerFactory.getLogger(SkuCategoryController.class);

    @Resource
    private SkuCategoryService skuCategoryService;

    @GetMapping("/test")
    public SkuCategoryPO test(){
        return skuCategoryService.test();

    }
}
