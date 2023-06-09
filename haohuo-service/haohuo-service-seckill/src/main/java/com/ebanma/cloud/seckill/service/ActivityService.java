package com.ebanma.cloud.seckill.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.github.pagehelper.PageInfo;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface ActivityService extends Service<Activity> {

    PageInfo searchInfoBypage(ActivitySearchInfoDto activitySearchInfoDto);

    int saveActivity(ActivitySaveDto saveDto);

    Object getRedisInfo(String activity);
}
