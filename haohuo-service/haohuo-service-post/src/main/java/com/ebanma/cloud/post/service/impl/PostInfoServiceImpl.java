package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.PostInfo;
import com.ebanma.cloud.post.service.PostInfoService;
import com.ebanma.cloud.post.dao.PostInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author banma-
* @description 针对表【post_info(推荐帖信息表)】的数据库操作Service实现
* @createDate 2023-06-06 15:47:55
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo>
    implements PostInfoService{

}




