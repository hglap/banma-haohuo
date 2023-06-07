package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.mall.service.SkuAttachmentService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(value = "商品附件管理", tags = "商品附件管理")
@RestController
@RequestMapping("/attachment")
public class SkuAttachmentController {
    private Logger logger = LoggerFactory.getLogger(SkuAttachmentController.class);

    @Resource
    private SkuAttachmentService skuAttachmentService;

}
