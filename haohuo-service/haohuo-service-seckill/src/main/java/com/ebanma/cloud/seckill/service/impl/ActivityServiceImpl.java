package com.ebanma.cloud.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.util.BeanUtil;
import com.ebanma.cloud.common.util.IdWorker;
import com.ebanma.cloud.seckill.dao.ActivityMapper;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.ebanma.cloud.seckill.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
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

    @Resource
    private ActivityMapper activityMapper;


    @Resource
    private RedisTemplate redisTemplate;

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
        IdWorker id = new IdWorker();
        Activity activity = new Activity();
        BeanUtils.copyProperties(saveDto, activity);
        activity.setId(id.nextId());
        activity.setCreateTime(new Date());
        redisTemplate.opsForValue().set("activity", JSON.toJSONString(saveDto),20, TimeUnit.HOURS);
        return activityMapper.saveActivity(activity);
    }

    @Override
    public Object getRedisInfo(String activity) {
        return redisTemplate.opsForValue().get(activity);
    }
}
