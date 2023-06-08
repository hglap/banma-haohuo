package com.ebanma.cloud.post.dao;

import com.ebanma.cloud.post.model.PostCommentLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author banma-
* @description 针对表【post_comment_like】的数据库操作Mapper
* @createDate 2023-06-06 15:47:55
* @Entity com.ebanma.cloud.post.domain.PostCommentLike
*/
@Mapper
public interface PostCommentLikeMapper extends BaseMapper<PostCommentLike> {

}




