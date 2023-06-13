package com.ebanma.cloud.post.web;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.config.DateConfig;
import com.ebanma.cloud.post.dao.PostCommentLikeMapper;
import com.ebanma.cloud.post.dao.PostCommentMapper;
import com.ebanma.cloud.post.model.PostComment;
import com.ebanma.cloud.post.model.po.PostCommentLikePO;
import com.ebanma.cloud.post.model.po.PostCommentPO;
import com.ebanma.cloud.post.service.PostCommentLikeService;
import com.ebanma.cloud.post.service.PostCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 评论增删改查
 * @author hugang
 * @date 2023/6/9 13:20
 * @version 1.0
 */
@RestController
@RequestMapping("/post/comment")
public class PostCommentController {
    @Resource
    private PostCommentService postCommentService;

    @Resource
    private PostCommentLikeService postCommentLikeService;

    @Resource
    private PostCommentLikeMapper postCommentLikeMapper;

    @Resource
    private DateConfig dateConfig;

    @Resource
    private PostCommentMapper postCommentMapper;

    @Resource
    private  RedisTemplate<String, String> redisTemplate;

    private static final String KEY_TEMPLATE = "likeOrNo_";


    /**
     * @description: 查询一级评论
     * @author hugang
     * @date 2023/6/9 19:46
     * @version 1.0
     */
    @PostMapping("/queryFirstComment")
    public Result queryFirstComment(@RequestBody PostCommentPO postComment) {
        ValueOperations<String, String> opsValue = redisTemplate.opsForValue();
        //得到1级评论
        QueryWrapper<PostCommentPO> wrapper = new QueryWrapper<>();
        wrapper.eq("post_id",postComment.getPostId());
        wrapper.eq("parent_id",0);
        wrapper.eq("dr",0);
        List<PostCommentPO> postCommentPOS = postCommentMapper.selectList(wrapper);
        List<PostComment> postCommentList=new ArrayList<>();
        //PO转VO
        for (PostCommentPO commentPO:postCommentPOS) {
            PostComment postComment1 = BeanUtil.copyProperties(commentPO, PostComment.class);
            getIsLike(opsValue, postComment1);
            postCommentList.add(postComment1);
        }
        //查询1级评论下的1条二级评论
        for (PostComment postComment2:postCommentList) {
            QueryWrapper<PostCommentPO> wrapper2 = new QueryWrapper<>();
            wrapper2.eq("parent_id",postComment2.getId());
            wrapper2.orderByDesc("create_user");
            wrapper.eq("dr",0);
            wrapper2.last("limit 1");
            PostCommentPO postCommentPO = postCommentMapper.selectOne(wrapper2);
            PostComment postComment3 = BeanUtil.copyProperties(postCommentPO, PostComment.class);
            getIsLike(opsValue, postComment3);
            postComment2.setPostComment(postComment3);
        }
        //获取一个一级评论下有多少个二级评论
        QueryWrapper<PostCommentPO> wrapper10 = new QueryWrapper<>();
        wrapper10.select("count(*) AS total","parent_id");
        wrapper10.groupBy("parent_id");
        wrapper10.eq("dr",0);
        List<PostCommentPO> postCommentPOS1 = postCommentMapper.selectList(wrapper10);
        for (PostComment comment : postCommentList) {
            for (PostCommentPO postCommentPO : postCommentPOS1) {
                if(comment.getId().equals(postCommentPO.getParentId())) {
                    comment.setTotal(postCommentPO.getTotal());
                }
            }
        }
        return ResultGenerator.genSuccessResult(postCommentList);
    }
    /**
     * @description: 判断用户是否点赞了！
     * @author hugang
     * @date 2023/6/12 15:37
     * @version 1.0
     */
    private void getIsLike(ValueOperations<String, String> opsValue, PostComment postComment) {
        String key=KEY_TEMPLATE+ postComment.getId()+"_"+ postComment.getCreateUser();
        String s = opsValue.get(key);
        if (("".equals(s) || s == null)) {
            postComment.setLike(false);
        } else {
            postComment.setLike(true);
        }
    }

    /**
     * @description: 查询二级评论
     * @author hugang
     * @date 2023/6/9 19:47
     * @version 1.0
     */
    @PostMapping("/querySecondComment")
    public Result querySecondComment(@RequestBody PostCommentPO postComment) {
        ValueOperations<String, String> opsValue = redisTemplate.opsForValue();
        QueryWrapper<PostCommentPO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",postComment.getId());
        wrapper.orderByDesc("create_time");
        List<PostCommentPO> postCommentPOS = postCommentMapper.selectList(wrapper);
        List<PostComment> postCommentVOList = new ArrayList<>();
        for (PostCommentPO postCommentPO : postCommentPOS) {
            PostComment postComment10 = BeanUtil.copyProperties(postCommentPO, PostComment.class);
            getIsLike(opsValue,postComment10);
            postCommentVOList.add(postComment10);
        }
        return ResultGenerator.genSuccessResult(postCommentVOList);
    }
    /**
     * @description: 发布一级评论
     * @author hugang
     * @date 2023/6/9 13:23
     * @version 1.0
     */
    @PostMapping("/submitComment")
    public Result submitComment(@RequestBody PostCommentPO postComment) {
        postCommentService.save(postComment);
        return ResultGenerator.genSuccessResult();
    }
    /**
     * @description: 根据postId删除所有评论
     * @author hugang
     * @date 2023/6/9 13:52
     * @version 1.0
     */
    @PostMapping("/deleteByPostId")
    public Result deleteByPostId(@RequestBody List<String> postIds) {
        if(postIds!=null && postIds.size()>0){
            UpdateWrapper<PostCommentPO> updateWrapper = new UpdateWrapper<>();
            updateWrapper.lambda()
                    .in(PostCommentPO::getPostId, postIds)
                .set(PostCommentPO::getDr,1);
            postCommentService.update(updateWrapper);
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("未选择删除对象！");
    }
    /**
     * @description: 查评论总数
     * @author hugang
     * @date 2023/6/9 14:27
     * @version 1.0
     */
    @PostMapping("/countComments")
    public Result countComments(@RequestBody PostCommentPO postComment) {
        QueryWrapper<PostCommentPO> query = new QueryWrapper<>();
        query.eq("post_id",postComment.getPostId());
        long count = postCommentService.count(query);
        return ResultGenerator.genSuccessResult(count);
    }

    /**
     * @description: 【用不到！！！】
     * @author hugang
     * @date 2023/6/12 15:42
     * @version 1.0
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PostCommentPO> list = postCommentService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
