package com.ebanma.cloud.mall.model.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@ApiModel(value = "BasePO", description = "基础实体类")
public class BasePO {
    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "last_modified", fill = FieldFill.INSERT_UPDATE)
    private Date lastModified;

    @ApiModelProperty("修改人")
    @TableField(value = "last_modify_user", fill = FieldFill.INSERT_UPDATE)
    private String lastModifyUser;

    @ApiModelProperty("版本")
    @TableField(value = "version", fill = FieldFill.INSERT)
    @Version
    private Integer version;

}
