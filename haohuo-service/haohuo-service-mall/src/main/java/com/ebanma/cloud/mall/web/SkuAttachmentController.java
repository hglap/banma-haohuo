package com.ebanma.cloud.mall.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentDTO;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuAttachmentPO;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;
import com.ebanma.cloud.mall.service.SkuAttachmentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "根据关联ID和关联类型查询附件列表", notes = "【APP】根据关联ID和关联类型查询附件列表", httpMethod = "POST")
    @PostMapping("/getAttachmentList")
    public Result<List<SkuAttachmentVO>> getAttachmentList(@RequestBody @NotNull(message="关联ID列表和关联类型不能为空") SkuAttachmentSearchDTO skuAttachmentSearchDTO){
        return ResultGenerator.genSuccessResult(skuAttachmentService.getAttachmentList(skuAttachmentSearchDTO));
    }

    @ApiOperation(value = "根据关联ID列表和关联类型查询附件列表", notes = "【APP】根据关联ID列表和关联类型查询附件列表", httpMethod = "POST")
    @PostMapping("/getAttachmentListByRelationIdListAndRelationType")
    public Result<Map<String,List<SkuAttachmentVO>>> getAttachmentMap(@RequestBody @NotNull(message="关联ID列表和关联类型不能为空") SkuAttachmentSearchDTO skuAttachmentSearchDTO){
        return ResultGenerator.genSuccessResult(skuAttachmentService.getAttachmentMap(skuAttachmentSearchDTO));
    }

    @ApiOperation(value = "根据ID查询附件", notes = "【APP】根据ID查询附件", httpMethod = "GET")
    @GetMapping("/queryById")
    public Result<SkuAttachmentVO> getAttachmentList(@NotEmpty(message = "附件ID不能为空") String id){
        return ResultGenerator.genSuccessResult(skuAttachmentService.queryById(id));
    }

    @ApiOperation(value = "根据关联ID及关联类型删除附件", notes = "根据关联ID及关联类型删除附件", httpMethod = "POST")
    @GetMapping("/del")
    public Result<Boolean> del(@RequestBody @NotNull(message = "入参不能为空") SkuAttachmentDTO skuAttachmentDTO) {
        return ResultGenerator.genSuccessResult(skuAttachmentService.del(skuAttachmentDTO));
    }




}
