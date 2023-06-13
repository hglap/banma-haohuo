package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.po.PostReadPO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.service.PostReadService;
import com.ebanma.cloud.post.dao.PostReadMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
* @author banma-0163
* @description 针对表【post_read】的数据库操作Service实现
* @createDate 2023-06-06 19:11:08
*/
@Service
public class PostReadServiceImpl extends ServiceImpl<PostReadMapper, PostReadPO>
    implements PostReadService{

    @Override
    @Async
    public void addRead(PostInfoVO postInfoVO) {
        PostReadPO postReadPO = new PostReadPO();
        postReadPO.setPostId(postInfoVO.getId());
        postReadPO.setUserId(postInfoVO.getUserId());
        save(postReadPO);
    }
}




