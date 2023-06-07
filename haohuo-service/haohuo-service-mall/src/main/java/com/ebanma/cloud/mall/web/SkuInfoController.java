package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<PageInfo> queryList(@RequestBody SkuInfoInsertDTO skuInfoInsertDTO){
        return ResultGenerator.genSuccessResult(skuInfoService.queryList(skuInfoInsertDTO));
    }

}
