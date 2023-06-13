package com.ebanma.cloud.post.service;

import com.ebanma.cloud.post.model.po.PostReadPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.post.model.vo.PostInfoVO;

/**
* @author banma-0163
* @description 针对表【post_read】的数据库操作Service
* @createDate 2023-06-06 19:11:08
*/
public interface PostReadService extends IService<PostReadPO> {

    void addRead(PostInfoVO postInfoVO);
}
