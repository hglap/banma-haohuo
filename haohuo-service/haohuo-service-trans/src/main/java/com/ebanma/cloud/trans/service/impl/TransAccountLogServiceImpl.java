package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.TransAccountLogMapper;
import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.model.TransInfo;
import com.ebanma.cloud.trans.service.TransAccountLogService;
import com.ebanma.cloud.trans.service.TransAccountService;
import com.ebanma.cloud.trans.service.TransInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.util.StringUtil;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class TransAccountLogServiceImpl extends AbstractService<TransAccountLog> implements TransAccountLogService {

    @Resource
    private TransInfoService transInfoService;

    @Resource
    private TransAccountService transAccountService;

    @Resource
    private TransAccountLogMapper transAccountLogMapper;

    @Override
    public void record(TransAccountLog transAccountLog) {
        //TODO
        //1.幂等，分布式锁或者订单表？

        //TODO
        //2.查询账户,userId从token获取
        String userId = "";
        Integer bizType = transAccountLog.getBizType();
        if (bizType == null) {
            //TODO 全局异常处理器。自定义业务异常
            //抛出业务异常
        }
        String transType = bizType == 0 ? "积分" : "红包";
        Condition condition = new Condition(TransInfo.class);
        condition.createCriteria().andEqualTo("user_id", userId).andEqualTo("transType", transType);
        //3.如果账户不存在，需要新建
        if (transInfoService.findByCondition(condition).size() == 0) {
            TransInfo transInfo = new TransInfo();
            transInfo.setUserId(userId);
            transInfo.setTransType(transType);
            transInfo.setTransTypeCode(bizType == 0 ? "0001" : "0002");
            transInfoService.save(transInfo);
        }
        //4.校验交易值是否满足扣减
        //5.账务事务增减
        //6.流水记录
        //7.解锁
    }
}
