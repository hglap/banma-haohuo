package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.trans.dao.TransAccountLogMapper;
import com.ebanma.cloud.trans.model.*;
import com.ebanma.cloud.trans.service.*;
import com.ebanma.cloud.trans.vo.TransAccountLogDTO;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.vo.TransAccountLogVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
@Slf4j
public class TransAccountLogServiceImpl extends AbstractService<TransAccountLog> implements TransAccountLogService {

    @Resource
    private TransInfoService transInfoService;
    @Resource
    private TransAccountService transAccountService;
    @Resource
    private TransOrderService transOrderService;
    @Resource
    private TransAccountLogMapper transAccountLogMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private final Map<String, TransStrategy> strategyMap = new ConcurrentHashMap<>();

    //通过构造方法将策略注入策略池
    public TransAccountLogServiceImpl(Map<String, TransStrategy> strategyMap) {
        this.strategyMap.clear();
        this.strategyMap.putAll(strategyMap);
    }


    /**
     * 账务增减
     *
     * @param transAccountLog
     * @throws Exception
     */
    @Override
    public void record(TransAccountLog transAccountLog) throws Exception {
        //1.新建订单作幂等，流水号由业务流水号、交易类型、用户id、流水值拼接而成
        String userId = StringUtils.isBlank(transAccountLog.getUserId()) ? "001" : transAccountLog.getUserId();
        transAccountLog.setUserId(userId);
        String serialNumber = transAccountLog.getBizSerialNumber() + transAccountLog.getLogType() + userId + transAccountLog.getAmount();
        Condition condition = new Condition(TransOrder.class);
        condition.createCriteria().andEqualTo("serialNumber", serialNumber);
        List<TransOrder> transOrders = transOrderService.findByCondition(condition);
        TransOrder transOrder;
        if (transOrders.size() > 0) {
            Integer orderStatus = transOrders.get(0).getOrderStatus();
            //状态：下单，有相同订单并发中；成功，此次为重复订单。此次请求需要中止。
            if (orderStatus == 0 || orderStatus == 1) {
                return;
            }
            if (orderStatus == 2) {
                transOrder = transOrders.get(0);
                transOrder.setOrderStatus(0);
                transOrderService.update(transOrder);
            } else {
                log.error("账务订单异常，账务订单为{}", transOrders.get(0));
                MallException.fail("账务订单异常");
                return;
            }
        } else {
            transOrder = new TransOrder();
            BeanUtils.copyProperties(transAccountLog, transOrder);
            transOrder.setId(null);
            transOrder.setSerialNumber(serialNumber);
            transOrder.setOrderStatus(0);
            transOrderService.save(transOrder);
        }
        //2.查询账户，如果账户不存在，需要新建
        if (transInfoService.findBy("userId", userId) == null) {
            //此次作出修改，创建账户不再区分积分与红包，此处账户类型均为混合账户。且默认code为0003，账户名为userId与code的拼接值。
            TransInfo transInfo = new TransInfo();
            transInfo.setUserId(userId);
            transInfo.setTransType("混合");
            transInfo.setTransTypeCode("0003");
            transInfo.setTransId(userId + "0003");
            transInfo.setStatus("1");
            transInfoService.save(transInfo);
        }
        //如果请求中未携带transId，此处补全
        if (StringUtils.isBlank(transAccountLog.getTransId())) {
            transAccountLog.setTransId(userId + "0003");
        }
        //3.校验交易值是否满足扣减；操作账务事务增减及流水记录
        Integer bizType = transAccountLog.getBizType();
        String userLockKey = null;
        try {
            //上锁，防止统一用户并发操作查询与增减导致的错误
            userLockKey = "userLock:" + serialNumber;
            Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent(userLockKey, "lock");
            if (lock) {
                if (bizType == null) {
                    transOrder.setOrderStatus(2);
                    transOrderService.update(transOrder);
                    MallException.fail("代币类型不能为空");
                }
                //策略模式对积分或红包进行操作更改
                TransStrategy transStrategy = strategyMap.get(Integer.toString(bizType));
                if (transStrategy == null) {
                    log.error("账务流水请求的代币类型为{}", bizType);
                    MallException.fail("代币类型错误");
                }
                transStrategy.doTrans(transAccountLog);
            } else {
                MallException.fail("业务繁忙，请稍后重试");
            }
        } catch (Exception e) {
            transOrder.setOrderStatus(2);
            transOrderService.update(transOrder);
            throw e;
        } finally {
            //解锁
            if (StringUtils.isNotBlank(userLockKey)) {
                String lockValue = stringRedisTemplate.opsForValue().get(userLockKey);
                if (lockValue.equals("lock")) {
                    stringRedisTemplate.delete(userLockKey);
                }
            }
        }
        //4.账务订单状态维护
        transOrder.setOrderStatus(1);
        transOrderService.update(transOrder);
    }

    /**
     * 账务查询
     *
     * @param transAccountLogSearchVO
     * @return
     * @throws Exception
     */
    @Override
    public TransAccountLogVO searchByCondition(TransAccountLogSearchVO transAccountLogSearchVO) throws Exception {
        //分页查询明细
        String userId = StringUtils.isBlank(transAccountLogSearchVO.getUserId()) ? "001" : transAccountLogSearchVO.getUserId();
        String transId = userId + "0003";
        TransAccountLog transAccountLog = new TransAccountLog();
        transAccountLog.setTransId(transId);
        List<TransAccountLog> transAccountLogs = transAccountLogMapper.searchLogsWithRedPacket(transAccountLog);
        //过滤代币类型
        Integer bizType = transAccountLogSearchVO.getBizType();
        if (bizType == null) {
            MallException.fail("代币类型不能为空");
        }
        List<TransAccountLog> filterResult = transAccountLogs.stream()
                .filter(item -> Objects.equals(item.getBizType(), bizType))
                .collect(Collectors.toList());
        List<TransAccountLogDTO> list = new ArrayList<>();
        if (filterResult != null) {
            //策略模式加载红包或者积分
            TransStrategy transStrategy = strategyMap.get(Integer.toString(bizType));
            if (transStrategy == null) {
                log.error("账务查询请求的代币类型为{}", bizType);
                MallException.fail("代币类型错误");
            }
            list = transStrategy.doSearchTrans(filterResult, transAccountLogSearchVO);
        }
        PageInfo pageInfo = new PageInfo(list);
        //查询并返回总积分
        Condition condition = new Condition(TransAccount.class);
        condition.createCriteria().andEqualTo("transId", transId);
        TransAccount transAccount = transAccountService.findByCondition(condition).get(0);
        TransAccountLogVO transAccountLogVO = new TransAccountLogVO(transId, transAccount.getBalance(), pageInfo);
        return transAccountLogVO;
    }
}
