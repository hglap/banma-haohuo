package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.PostComment;
import com.ebanma.cloud.post.service.PostCommentService;
import com.ebanma.cloud.post.dao.PostCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author banma-
* @description 针对表【post_comment】的数据库操作Service实现
* @createDate 2023-06-06 15:47:55
*/
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
    implements PostCommentService{

}




