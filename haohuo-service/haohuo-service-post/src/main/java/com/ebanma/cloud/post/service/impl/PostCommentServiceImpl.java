package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.po.PostCommentPO;
import com.ebanma.cloud.post.service.PostCommentService;
import com.ebanma.cloud.post.dao.PostCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author banma-0163
* @description 针对表【post_comment】的数据库操作Service实现
* @createDate 2023-06-06 19:10:08
*/
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostCommentPO>
    implements PostCommentService{

}




