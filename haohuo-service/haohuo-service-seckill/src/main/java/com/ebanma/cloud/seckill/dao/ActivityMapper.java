package com.ebanma.cloud.seckill.dao;

import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.seckill.model.po.Activity;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


public interface ActivityMapper extends Mapper<Activity> {
    List<Activity> searchBypage(@Param("createUserName") String createUserName,@Param("createTime") Timestamp createTime);

    int saveActivity(Activity saveDto);

    int countStatusIsUNPUBLISHED();

    void closeDoneActivity(Timestamp tt);
}