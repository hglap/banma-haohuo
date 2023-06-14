package com.ebanma.cloud.post.model.po;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName post_comment
 */
@Data
@TableName("post_comment")
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentPO implements Serializable{
    /**
     * 主键id
     */
    @TableId(value = "id",type= IdType.ASSIGN_ID)
    private Long id;

    /**
     * 帖子id
     */
    @TableField("post_id")
    private Long postId;

    /**
     * 回复内容
     */
    @TableField("content")
    private String content;

    /**
     * 0表示评论文章，若是评论的是评论则为被评论的评论id
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 评论被点赞数
     */
    @TableField("likes")
    private Integer likes;

    /**
     * 评论人名字
     */
    @TableField("commenter_name")
    private String commenterName;

    /**
     * 评论人头像
     */
    @TableField("commenter_avatar")
    private String commenterAvatar;

    /**
     * 评论人地址（省份）
     */
    @TableField("commenter_address")
    private String commenterAddress;

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

    /**
     * 一级评论下多少个二级评论
     */
    @TableField(exist = false)
    private Integer total;

    private static final long serialVersionUID = 1L;


}