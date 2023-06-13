package com.ebanma.cloud.post.model.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName post_comment_like
 */
@Data
@TableName("post_comment_like")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostCommentLikePO implements Serializable  {
    /**
     * 主键id
     */
    @TableId(value = "id",type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 评论id
     */
    @TableField("comment_id")
    private Long commentId;

    /**
     * 评论人id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

    /**
     * 更改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标记 0保留 1删除
     */
    @TableField("dr")
    private Integer dr;

    private static final long serialVersionUID = 1L;

}