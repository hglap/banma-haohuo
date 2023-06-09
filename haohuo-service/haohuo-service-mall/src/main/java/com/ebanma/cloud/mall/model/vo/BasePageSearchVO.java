package com.ebanma.cloud.mall.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 14:40
 * @description:
 */
@Data
@ApiModel(value = "TableBaseSearchVO", description = "分页入参基础类，可根据使用场景扩展使用")
public class BasePageSearchVO {

    @ApiModelProperty("页数")
    private Integer pageSize=10;

    @ApiModelProperty("当前页码")
    private Integer pageNum=1;

}
