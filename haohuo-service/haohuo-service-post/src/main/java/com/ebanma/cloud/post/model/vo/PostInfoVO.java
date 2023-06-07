package com.ebanma.cloud.post.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐帖信息表
 * @TableName post_info
 */
@Data
@TableName("post_info")
public class PostInfoVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 帖子ID
     */
    private Long postId;
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容(内容key)
     */
    private String content;

    /**
     * 使用状态（1正常 0非正常）
     */
    private String status;

    /**
     * 标签列表
     */
    private String topic;

    /**
     * 图片url数组
     */
    private String[] img;

    /**
     * 商品链接
     */
    private String sku;

    /**
     * 帖子点赞数量
     */
    private Integer likes;

    /**
     * 阅读量
     */
    private Integer views;

    /**
     * 帖子评论数量
     */
    private Integer comments;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    private static final long serialVersionUID = 1L;


}