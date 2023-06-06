package com.ebanma.cloud.post.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "post_info")
public class PostInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 帖子ID
     */
    @Column(name = "post_id")
    private String postId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户头像
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 帖子标题
     */
    @Column(name = "post_title")
    private String postTitle;

    /**
     * 帖子内容
     */
    @Column(name = "post_content")
    private String postContent;

    /**
     * 使用状态（1正常 0非正常）
     */
    private String status;

    /**
     * 标签列表
     */
    @Column(name = "tag_ids")
    private String tagIds;

    /**
     * 帖子点赞数量
     */
    @Column(name = "praise_count")
    private Integer praiseCount;

    /**
     * 帖子评论数量
     */
    @Column(name = "reply_count")
    private Integer replyCount;

    /**
     * 商品链接
     */
    @Column(name = "sku_url")
    private String skuUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modified_time")
    private Date modifiedTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取帖子ID
     *
     * @return post_id - 帖子ID
     */
    public String getPostId() {
        return postId;
    }

    /**
     * 设置帖子ID
     *
     * @param postId 帖子ID
     */
    public void setPostId(String postId) {
        this.postId = postId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户昵称
     *
     * @return nick_name - 用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置用户昵称
     *
     * @param nickName 用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取用户头像
     *
     * @return head_img - 用户头像
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置用户头像
     *
     * @param headImg 用户头像
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取帖子标题
     *
     * @return post_title - 帖子标题
     */
    public String getPostTitle() {
        return postTitle;
    }

    /**
     * 设置帖子标题
     *
     * @param postTitle 帖子标题
     */
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    /**
     * 获取帖子内容
     *
     * @return post_content - 帖子内容
     */
    public String getPostContent() {
        return postContent;
    }

    /**
     * 设置帖子内容
     *
     * @param postContent 帖子内容
     */
    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    /**
     * 获取使用状态（1正常 0非正常）
     *
     * @return status - 使用状态（1正常 0非正常）
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置使用状态（1正常 0非正常）
     *
     * @param status 使用状态（1正常 0非正常）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取标签列表
     *
     * @return tag_ids - 标签列表
     */
    public String getTagIds() {
        return tagIds;
    }

    /**
     * 设置标签列表
     *
     * @param tagIds 标签列表
     */
    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    /**
     * 获取帖子点赞数量
     *
     * @return praise_count - 帖子点赞数量
     */
    public Integer getPraiseCount() {
        return praiseCount;
    }

    /**
     * 设置帖子点赞数量
     *
     * @param praiseCount 帖子点赞数量
     */
    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * 获取帖子评论数量
     *
     * @return reply_count - 帖子评论数量
     */
    public Integer getReplyCount() {
        return replyCount;
    }

    /**
     * 设置帖子评论数量
     *
     * @param replyCount 帖子评论数量
     */
    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * 获取商品链接
     *
     * @return sku_url - 商品链接
     */
    public String getSkuUrl() {
        return skuUrl;
    }

    /**
     * 设置商品链接
     *
     * @param skuUrl 商品链接
     */
    public void setSkuUrl(String skuUrl) {
        this.skuUrl = skuUrl;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modified_time - 修改时间
     */
    public Date getModifiedTime() {
        return modifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedTime 修改时间
     */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}