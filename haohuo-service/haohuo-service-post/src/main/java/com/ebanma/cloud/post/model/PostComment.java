package com.ebanma.cloud.post.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName post_comment
 */
@TableName(value ="post_comment")
@Data
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
    private String dr;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PostComment other = (PostComment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPostId() == null ? other.getPostId() == null : this.getPostId().equals(other.getPostId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
            && (this.getCommenterName() == null ? other.getCommenterName() == null : this.getCommenterName().equals(other.getCommenterName()))
            && (this.getCommenterAvatar() == null ? other.getCommenterAvatar() == null : this.getCommenterAvatar().equals(other.getCommenterAvatar()))
            && (this.getCommenterAddress() == null ? other.getCommenterAddress() == null : this.getCommenterAddress().equals(other.getCommenterAddress()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPostId() == null) ? 0 : getPostId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getCommenterName() == null) ? 0 : getCommenterName().hashCode());
        result = prime * result + ((getCommenterAvatar() == null) ? 0 : getCommenterAvatar().hashCode());
        result = prime * result + ((getCommenterAddress() == null) ? 0 : getCommenterAddress().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", postId=").append(postId);
        sb.append(", content=").append(content);
        sb.append(", parentId=").append(parentId);
        sb.append(", likes=").append(likes);
        sb.append(", commenterName=").append(commenterName);
        sb.append(", commenterAvatar=").append(commenterAvatar);
        sb.append(", commenterAddress=").append(commenterAddress);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", dr=").append(dr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}