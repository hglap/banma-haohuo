
package com.ebanma.cloud.post.utils;

import com.alibaba.fastjson.JSONObject;
import com.ebanma.cloud.post.model.po.PostCommentLikePO;
import com.ebanma.cloud.post.service.PostCommentLikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author banma-
 */
@Log4j2
public class Redis2Mysql {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private PostCommentLikeService commentLikeService;
    /**
     * @description: 定时任务每天0点写入mysql数据库，持久化！
     * @author hugang
     * @date 2023/6/12 14:51
     * @version 1.0
     */
    @Scheduled(cron = "0 0 0 * * ? *")
    public void Redis2Mysql(){
    log.info("===========Redis2Mysql==============");
        ValueOperations<String, String> opsValue = redisTemplate.opsForValue();
        List<String> likeOrNo_ = getListKey("likeOrNo_");
        likeOrNo_.parallelStream()
                .forEach(s -> {
                    PostCommentLikePO postCommentLikePO = JSONObject.parseObject(s, PostCommentLikePO.class);
                    postCommentLikePO.setId(postCommentLikePO.getCommentId()+ postCommentLikePO.getUserId());
                    commentLikeService.saveOrUpdate(postCommentLikePO);
                });
    }
    /**
     * 根据前缀获取所有的key
     * 例如：likeOrNo_*
     */
    public List<String> getListKey(String prefix) {
        Set<String> keys = redisTemplate.keys("*");
        List<String> values = redisTemplate.opsForValue().multiGet(keys);
        return values;
    }

}
