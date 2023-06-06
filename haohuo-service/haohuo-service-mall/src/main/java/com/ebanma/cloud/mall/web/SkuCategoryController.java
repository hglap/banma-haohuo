package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.mall.model.po.SkuCategory;
import com.ebanma.cloud.mall.service.SkuCategoryService;
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
@RestController
@RequestMapping("/test")
public class SkuCategoryController {

    @Resource
    private SkuCategoryService skuCategoryService;

    @GetMapping("/test")
    public SkuCategory test(){
        return skuCategoryService.test();

    }
}
