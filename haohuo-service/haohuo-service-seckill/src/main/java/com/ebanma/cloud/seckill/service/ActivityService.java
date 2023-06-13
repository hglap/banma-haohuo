package com.ebanma.cloud.seckill.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.dto.Git;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivityGetInfoVo;
import com.ebanma.cloud.seckill.model.vo.SeckillGit;
import com.github.pagehelper.PageInfo;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface ActivityService extends Service<Activity> {

    PageInfo searchInfoBypage(ActivitySearchInfoDto activitySearchInfoDto);

    int saveActivity(ActivitySaveDto saveDto);

    Object getRedisInfo(String activity);

    ActivityGetInfoVo getInfo(String userId);

    SeckillGit seckill(String path, String userId, long activityId);


    Git redisTestRead(String key);
}
