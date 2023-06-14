package com.ebanma.cloud.post.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ebanma.cloud.post.model.po.PostCommentPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName post_comment
 */
@TableName(value ="post_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostComment implements Serializable {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 帖子id
     */
    private Long postId;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 0表示评论文章，若是评论的是评论则为被评论的评论id
     */
    private Long parentId;

    /**
     * 评论被点赞数
     */
    private Integer likes;
    /**
     * 是否点赞
     */
    private boolean isLike;

    /**
     * 评论人名字
     */
    private String commenterName;

    /**
     * 评论人头像
     */
    private String commenterAvatar;

    /**
     * 评论人地址（省份）
     */
    private String commenterAddress;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更改人
     */
    private String updateUser;

    /**
     * 更改时间
     */
    private Date updateTime;

    /**
     * 删除标记 0保留 1删除
     */
    private Integer dr;
    /**
     * 一级评论下多少个二级评论
     */
    private Integer total;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private PostComment postComment;


}