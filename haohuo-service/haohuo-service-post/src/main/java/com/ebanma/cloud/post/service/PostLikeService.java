package com.ebanma.cloud.post.service;

import com.ebanma.cloud.post.model.po.PostLikePO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author banma-0163
* @description 针对表【post_like】的数据库操作Service
* @createDate 2023-06-06 19:11:02
*/
public interface PostLikeService extends IService<PostLikePO> {

    boolean add(PostLikePO postLike);

    boolean remove(Long id);
}
