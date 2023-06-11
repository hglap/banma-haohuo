package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.*;
import com.ebanma.cloud.mall.model.po.SkuCategoryPO;
import com.ebanma.cloud.mall.model.vo.SkuCategoryVO;
import com.ebanma.cloud.mall.service.SkuCategoryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation(value = "分页查询分类列表", notes = "【服务端】分页查询分类列表", httpMethod = "POST")
    @PostMapping("/queryList")
    public Result<PageInfo> searchList(@RequestBody SkuCategorySearchDTO skuCategorySearchDTO){
        return ResultGenerator.genSuccessResult(skuCategoryService.queryList(skuCategorySearchDTO));
    }

    @ApiOperation(value = "分类新增", notes = "【服务端】分类新增", httpMethod = "POST")
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SkuCategoryInsertDTO skuCategoryInsertDTO){
        return ResultGenerator.genSuccessResult(skuCategoryService.add(skuCategoryInsertDTO));
    }


    @ApiOperation(value = "分类编辑", notes = "【服务端】分类编辑", httpMethod = "POST")
    @PostMapping("/edit")
    public Result<Boolean> edit(@RequestBody SkuCategoryEditDTO skuCategoryEditDTO){
        return ResultGenerator.genSuccessResult(skuCategoryService.edit(skuCategoryEditDTO));
    }

    @ApiOperation(value = "分类删除", notes = "【服务端】分类删除", httpMethod = "GET")
    @GetMapping("/del")
    public Result<Boolean> del(@RequestParam("id")  String id ){
        return ResultGenerator.genSuccessResult(skuCategoryService.del(id));
    }

    @ApiOperation(value = "分类修改导航栏状态", notes = "【服务端】分类修改导航栏状态", httpMethod = "GET")
    @GetMapping("/editStatus")
    public Result<Boolean> editStatus(@RequestParam("id")  String id , @RequestParam("useStatus")String useStatus){
        return ResultGenerator.genSuccessResult(skuCategoryService.editStatus(id,useStatus));
    }

    @ApiOperation(value = "分类获取可用列表", notes = "分类获取可用列表", httpMethod = "GET")
    @GetMapping("/getUseList")
    public Result<List<SkuCategoryVO>> editStatus(){
        return ResultGenerator.genSuccessResult(skuCategoryService.getUseList());
    }
}
