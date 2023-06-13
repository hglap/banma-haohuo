package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.po.PostLikePO;
import com.ebanma.cloud.post.service.PostLikeService;
import com.ebanma.cloud.post.dao.PostLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
* @author banma-0163
* @description 针对表【post_like】的数据库操作Service实现
* @createDate 2023-06-06 19:11:02
*/
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLikePO>
    implements PostLikeService{


    @Override
    public boolean add(PostLikePO postLike) {
        Boolean flag = null;

        try {
            flag = saveThis(postLike).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * 试试 async方法
     *
     * @param postLike 后像
     * @return {@link Future}<{@link Boolean}>
     */
    @Async
    public Future<Boolean> saveThis(PostLikePO postLike){
        return new AsyncResult<>(save(postLike));
    }

    @Override
    public boolean remove(Long id) {
        try {
            Boolean flag = removeThis(id).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean removeByCondition(PostLikePO postLike) {
        LambdaQueryWrapper<PostLikePO> lq = Wrappers.lambdaQuery();
        int delete = this.getBaseMapper().delete(lq.eq(PostLikePO::getPostId, postLike.getPostId())
                .eq(PostLikePO::getUserId, postLike.getUserId()));
        return delete > 0;
    }

    /**
     * 删除此
     * 试试 async方法
     *
     * @param id id
     * @return {@link Future}<{@link Boolean}>
     */
    @Async
    public Future<Boolean> removeThis(Long id){
        return new AsyncResult<>(removeById(id));
    }
}




