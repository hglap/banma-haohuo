package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.PostLike;
import com.ebanma.cloud.post.service.PostLikeService;
import com.ebanma.cloud.post.dao.PostLikeMapper;
import org.springframework.stereotype.Service;

/**
* @author banma-
* @description 针对表【post_like】的数据库操作Service实现
* @createDate 2023-06-06 15:47:55
*/
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike>
    implements PostLikeService{

}



