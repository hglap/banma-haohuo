package com.ebanma.cloud.post.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.config.DateConfig;
import com.ebanma.cloud.post.dao.PostCommentLikeMapper;
import com.ebanma.cloud.post.model.po.PostCommentLikePO;
import com.ebanma.cloud.post.model.po.PostCommentPO;
import com.ebanma.cloud.post.service.PostCommentLikeService;
import com.ebanma.cloud.post.service.PostCommentService;
import com.ebanma.cloud.post.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/post/comment/like")
public class PostCommentLikeController {
    @Resource
    private PostCommentLikeService postCommentLikeService;

    @Resource
    private PostCommentLikeMapper likeMapper;

    @Resource
    private PostCommentService postCommentService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private DateConfig dateConfig;

    private static final String KEY_TEMPLATE = "likeOrNo_";
    @PostMapping("/likeOrNo")
    @Transactional
    public Result likeOrNo(@RequestBody PostCommentLikePO postCommentLikePO) {
        QueryWrapper<PostCommentLikePO> wrapper=new QueryWrapper<>();
        Long commentId=postCommentLikePO.getCommentId();
        Long userId=postCommentLikePO.getUserId();
        wrapper.eq("comment_id", commentId);
        wrapper.eq("user_id", userId);
        wrapper.eq("dr", "0");
        List<PostCommentLikePO> likePOList = likeMapper.selectList(wrapper);
        if(likePOList!=null && likePOList.size()>0){
            PostCommentLikePO postCommentLikePO1 = likePOList.get(0);
            postCommentLikePO1.setDr(1);
            //如果找到了这条记录，则删除该记录
            boolean b1 = postCommentLikeService.saveOrUpdate((postCommentLikePO1));
            //同时文章的点赞数减1
            PostCommentPO commentPO = postCommentService.getById(postCommentLikePO1.getCommentId());
            commentPO.setLikes(commentPO.getLikes()-1);
            boolean b2 = postCommentService.saveOrUpdate(commentPO);
        }else{
            //没有找到记录，点赞表新增一条数据
            boolean save = postCommentLikeService.save(postCommentLikePO);
            //评论表点赞数+1
            PostCommentPO commentPO = postCommentService.getById(commentId);
            commentPO.setLikes(commentPO.getLikes() + 1);
            boolean c1 = postCommentService.saveOrUpdate(commentPO);
        }
        return ResultGenerator.genSuccessResult();
    }
    @PostMapping("/likeOrNoRedis")
    public Result likeOrNoRedis(@RequestBody PostCommentLikePO postCommentLikePO) {
        postCommentLikePO.setCreateTime(new Date())
                .setCreateUser(dateConfig.getUserName())
                .setDr(0);
        PostCommentPO commentPO=null;
        ValueOperations<String, String> opsValue = redisTemplate.opsForValue();
        Long commentId=postCommentLikePO.getCommentId();
        Long userId=postCommentLikePO.getUserId();
        String key=KEY_TEMPLATE+commentId+"_"+userId;
        //Java对象转换成JSON字符串
        String jsonString = JSONObject.toJSONString(postCommentLikePO);
        Boolean aBoolean = opsValue.setIfAbsent(key, jsonString);
        //没有查到，点赞，存数据库表
        if(aBoolean){
            commentPO = postCommentService.getById(commentId);
            commentPO.setLikes(commentPO.getLikes() + 1);
            boolean c1 = postCommentService.saveOrUpdate(commentPO);
            return ResultGenerator.genSuccessResult("点赞成功");
        }else{
            opsValue.getAndDelete(key);
            commentPO = postCommentService.getById(commentId);
            commentPO.setLikes(commentPO.getLikes()-1);
            boolean b2 = postCommentService.saveOrUpdate(commentPO);
            return ResultGenerator.genSuccessResult("取消成功");
        }
    }
}
