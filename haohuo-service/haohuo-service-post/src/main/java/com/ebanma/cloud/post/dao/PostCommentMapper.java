package com.ebanma.cloud.post.dao;

import com.ebanma.cloud.post.model.PostComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author banma-
* @description 针对表【post_comment】的数据库操作Mapper
* @createDate 2023-06-06 15:47:55
* @Entity com.ebanma.cloud.post.domain.PostComment
*/
@Mapper
public interface PostCommentMapper extends BaseMapper<PostComment> {

}




