package com.ebanma.cloud.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.common.util.IdWorker;
import com.ebanma.cloud.seckill.dao.ActivityMapper;
import com.ebanma.cloud.seckill.model.BusinessException;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.dto.SeckillMessageDto;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivityGetInfoVo;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.ebanma.cloud.seckill.model.vo.SeckillGit;
import com.ebanma.cloud.seckill.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author CodeGenerator
 * @date 2023/06/06
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class ActivityServiceImpl extends AbstractService<Activity> implements ActivityService {

    private Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private RedissonClient redissonClient;


    private final StringBuffer stringBuffer = new StringBuffer();

    private IdWorker id = new IdWorker();

    @Override
    public PageInfo searchInfoBypage(ActivitySearchInfoDto activitySearchInfoDto) {
        List<ActivitySearchInfoVo> searchInfo = new ArrayList<ActivitySearchInfoVo>();
        PageHelper.startPage(activitySearchInfoDto.getPage(), activitySearchInfoDto.getSize());
        List<Activity> list = activityMapper.searchBypage(activitySearchInfoDto.getCreateUserName(),activitySearchInfoDto.getCreateTime());
        PageInfo pageInfo = new PageInfo(list);
        searchInfo = BeanUtil.copyList(list,ActivitySearchInfoVo.class);
        pageInfo.setList(searchInfo);
        return pageInfo;
    }

    @Override
    public int saveActivity(ActivitySaveDto saveDto) {

        Activity activity = new Activity(
                id.nextId(),
                saveDto.getStartDate(),
                saveDto.getStartTime(),
                saveDto.getDuration(),
                0,
                new Timestamp(System.currentTimeMillis()),
                saveDto.getCreateUserId()+"",
                1,
                1,
                (int) saveDto.getAmount(),
                saveDto.getCreateUserName()
        );
        return this.saveActivityByTransaction(activity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = RuntimeException.class)
    public int saveActivityByTransaction(Activity activity){
       int i = activityMapper.saveActivity(activity);
       int num = activityMapper.countStatusIsUNPUBLISHED();
       if(i == 0){
           throw new BusinessException("新增失败");
       }else if(num > 1){
           throw new BusinessException("已存在待发布活动，无法新增");
       }
        LocalDateTime start = LocalDateTime.of(activity.getStartDate().toLocalDate(), activity.getStartTime().toLocalTime());
        long endTimeMillis = start.toInstant(ZoneOffset.of("+8")).toEpochMilli()+activity.getDuration()* 1000L;
        long during =endTimeMillis- System.currentTimeMillis();
       redisTemplate.opsForValue().set("activity", activity,during, TimeUnit.MILLISECONDS);
       redisTemplate.opsForValue().set("activityId", activity.getId(), during, TimeUnit.MILLISECONDS);
       redisTemplate.opsForValue().set("activityEndTimeMillis", endTimeMillis,during, TimeUnit.MILLISECONDS);
       this.getGiftList(activity.getAmount(),during);
       return i;
    }

    private void getGiftList(long amount, long during) {
        String key = "ActivityGift"+redisTemplate.opsForValue().get("activityId");
        for(int i = 0; i < amount; i++) {
           String gitName = stringBuffer.append("积分").append(i).toString();
            stringBuffer.setLength(0);
            redisTemplate.opsForList().rightPush(key,gitName);
        }
        redisTemplate.expire("ActivityGift",during,TimeUnit.MILLISECONDS);
    }

    @Override
    public Object getRedisInfo(String activity) {
        System.out.println("_______________________________");
        BoundListOperations<String,Object> bound = redisTemplate.boundListOps("ActivityGift");
        Activity activity1 = (Activity) redisTemplate.opsForValue().get("activity");
        String path = (String)redisTemplate.opsForHash().get("userPaths", "cui");
        System.out.println("path :" +path);
        System.out.println(JSON.toJSONString(activity1));
        System.out.println(bound.range(1,20).toString());
        bound.rightPop();
        return  bound.rightPop();
    }

    @Override
    public ActivityGetInfoVo getInfo(String userId) {
        ActivityGetInfoVo vo = new ActivityGetInfoVo();
        // 查询redis中有没有待发布活动
        Activity activity = (Activity) redisTemplate.opsForValue().get("activity");
        if(activity == null){
            throw new BusinessException("无待发布活动");
        }
        // 生产path
        String path = stringBuffer.append(activity.getId()).append("*").append(userId).append("*").append("seckill").toString();
        stringBuffer.setLength(0);
        // 将path存入redis中
        if(redisTemplate.opsForHash().putIfAbsent("userPaths",userId,path)){
            long during = this.getDuration();
            redisTemplate.expire("userPaths",during,TimeUnit.MILLISECONDS);
        };
        BeanUtils.copyProperties(activity,vo);
        vo.setPath(path);
        vo.setActivityId(activity.getId());
        return vo;
    }



    @Override
    public SeckillGit seckill(String path, String userId, long activityId) {
        //1.用户锁-避免用户重复提交抽奖
       RLock redissonLock = redissonClient.getLock("seckill");
        String gitName = "空";
        SeckillGit seckillGit = new SeckillGit();
        try {
            // TODO 获取分布式锁
            redissonLock.lock();
            // TODO 获取奖品
            BoundListOperations<String,Object> bound = redisTemplate.boundListOps("ActivityGift"+redisTemplate.opsForValue().get("activityId"));
            gitName = (String) bound.rightPop();
            // TODO 将path 放入redis中
            if(gitName != null){
                redisTemplate.opsForSet().add("getGitPaths",path);
                redisTemplate.expire("getGitPaths",getDuration(),TimeUnit.MILLISECONDS);
            }else{
               seckillGit.setGitName("空");
               return seckillGit;
            }
            // TODO 生产消息
            SeckillMessageDto seckillMessageDto = new SeckillMessageDto(
                    id.nextId(),
                    userId,
                    activityId,
                    new Timestamp(System.currentTimeMillis()),
                    gitName
            );
            // TODO 将消息发送到kafka中，并校验返回值
            sendMessage(seckillMessageDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // TODO 释放锁
            redissonLock.unlock();
        }
        // TODO 返回奖品
        seckillGit.setGitName(gitName);
        return seckillGit;
    }

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public boolean sendMessage(SeckillMessageDto msg) {
        kafkaTemplate.send("seckill", JSON.toJSONString(msg)).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("发送消息失败：{}" , ex.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, String> result) {

            }
        });
        return true;
    }

    private long getDuration() {
        long end = (long)redisTemplate.opsForValue().get("activityEndTimeMillis");
        long during =end - System.currentTimeMillis();
        log.info("during:{};end:{};now:{}",during,end,new Date());
        return during;
    }
}
