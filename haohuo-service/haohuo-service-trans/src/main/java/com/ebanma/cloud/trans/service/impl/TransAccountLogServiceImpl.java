package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.vo.TransAccountLogDTO;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.vo.TransAccountLogVO;
import com.ebanma.cloud.trans.dao.TransAccountLogMapper;
import com.ebanma.cloud.trans.model.*;
import com.ebanma.cloud.trans.service.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


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
    private RedPacketService redPacketService;

    @Resource
    private TransRedPacketService transRedPacketService;

    @Resource
    private TransAccountLogMapper transAccountLogMapper;

    /**
     * 账务增减
     *
     * @param transAccountLog
     * @throws Exception
     */
    @Override
    public void record(TransAccountLog transAccountLog) throws Exception {
        //TODO 入参校验注解化
        //TODO
        //1.幂等，分布式锁或者订单表？

        //TODO
        //2.查询账户,userId从token获取
        String userId = "001";
        //3.如果账户不存在，需要新建
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
        //4.校验交易值是否满足扣减
        //5.账务事务增减及流水记录
        Integer bizType = transAccountLog.getBizType();
        if (bizType == null) {
            //TODO 全局异常处理器。自定义业务异常
            throw new Exception("代币类型不能为空");
        } else if (bizType == 0) {
            TransAccount transAccount = checkPoints(transAccountLog);
            transPoints(transAccountLog, transAccount);
        } else if (bizType == 1) {
            TransAccount transAccount = checkRedPacket(transAccountLog);
            transRedPacket(transAccountLog, transAccount);
        } else {
            //TODO 全局异常处理器。自定义业务异常
            throw new Exception("代币类型错误");
        }
        //6.解锁

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
        String userId = "001";
        String transId = userId + "0003";
        TransAccountLog transAccountLog = new TransAccountLog();
        transAccountLog.setTransId(transId);
        List<TransAccountLog> transAccountLogs = transAccountLogMapper.searchLogsWithRedPacket(transAccountLog);
        //过滤代币类型
        Integer bizType = transAccountLogSearchVO.getBizType();
        if (bizType == null || (bizType != 0 && bizType != 1)) {
            //TODO 自定义异常
            throw new Exception("代币类型错误！");
        }
        List<TransAccountLog> filterResult = transAccountLogs.stream()
                .filter(item -> Objects.equals(item.getBizType(), bizType))
                .collect(Collectors.toList());
        List<TransAccountLogDTO> list = new ArrayList<>();
        //积分，过滤增加或支出或全部，且封装DTO
        if (bizType == 0) {
            if (transAccountLogSearchVO.getLogType() != null) {
                filterResult = filterResult.stream()
                        .filter(item -> Objects.equals(item.getLogType(), transAccountLogSearchVO.getLogType()))
                        .collect(Collectors.toList());
            }
            for (int i = 0; i < filterResult.size(); i++) {
                TransAccountLogDTO transAccountLogDTO = new TransAccountLogDTO();
                BeanUtils.copyProperties(filterResult.get(i), transAccountLogDTO);
                list.add(transAccountLogDTO);
            }
        }
        //红包，过滤未使用、已使用、已过期、7日内过期(独有)
        if (bizType == 1) {
            Integer redPacketStatus = transAccountLogSearchVO.getRedPacketStatus();
            //封装返回DTO，更新红包过期状态
            List<TransAccountLog> logs = filterResult.stream().filter(item -> item.getLogType() == 0).collect(Collectors.toList());
            for (int i = 0; i < logs.size(); i++) {
                TransAccountLogDTO transAccountLogDTO = new TransAccountLogDTO();
                BeanUtils.copyProperties(logs.get(i), transAccountLogDTO);
                if (logs.get(i).getRedPacket() != null) {
                    transAccountLogDTO = updateDTOAndRedPacket(logs.get(i).getRedPacket(), transAccountLogDTO);
                }
                list.add(transAccountLogDTO);
            }
            if (redPacketStatus != null) {
                list = list.stream()
                        .filter(item -> Objects.equals(item.getRedPacketStatus(), redPacketStatus))
                        .collect(Collectors.toList());
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        //TODO 增加总积分（按需要）
        Condition condition = new Condition(TransAccount.class);
        condition.createCriteria().andEqualTo("transId", transId);
        TransAccount transAccount = transAccountService.findByCondition(condition).get(0);
        TransAccountLogVO transAccountLogVO = new TransAccountLogVO(transId, transAccount.getBalance(), pageInfo);
        return transAccountLogVO;
    }

    /**
     * 账务查询时更新红包状态及DTO
     *
     * @param redPacket
     * @param transAccountLogDTO
     */
    private TransAccountLogDTO updateDTOAndRedPacket(RedPacket redPacket, TransAccountLogDTO transAccountLogDTO) {
        Date expireTime = redPacket.getExpireTime();
        Integer status = redPacket.getStatus();
        transAccountLogDTO.setRedPacketExpireTime(expireTime);
        transAccountLogDTO.setRedPacketStatus(status);
        //红包状态未使用的，需要重新校验红包状态
        if (status == 0) {
            long time = (expireTime.getTime() - new Date().getTime());
            if (time <= 0) {
                //更新DTO状态及红包状态
                transAccountLogDTO.setRedPacketStatus(2);
                redPacket.setStatus(2);
                redPacketService.update(redPacket);
            }
            if (time <= 1000 * 60 * 60 * 24 * 7) {
                //更新DTO状态
                transAccountLogDTO.setRedPacketStatus(3);
            }
        }
        return transAccountLogDTO;
    }

    /**
     * 红包增减及流水记录
     *
     * @param transAccountLog
     */
    private void transRedPacket(TransAccountLog transAccountLog, TransAccount transAccount) {
        //增加红包
        if (transAccountLog.getLogType() == 0) {
            //根据金额创建红包 TODO 红包过期
            RedPacket redPacket = new RedPacket();
            String uuid = UUID.randomUUID().toString();
            redPacket.setRedPacketId(uuid);
            redPacket.setRedPacketAmount(transAccountLog.getAmount());
            redPacket.setStatus(0);
            redPacket.setExpireTime(getMonthDate(new Date(), 1));
            redPacket.setCreateOn(new Date());
            redPacket.setCreateBy(transAccountLog.getTransId());
            //增加红包及账户红包关系表
            redPacketService.save(redPacket);
            TransRedPacket transRedPacket = new TransRedPacket();
            transRedPacket.setTransId(transAccountLog.getTransId());
            transRedPacket.setRedPacketId(uuid);
            transRedPacketService.save(transRedPacket);
            //流水记录中记录红包id
            transAccountLog.setRedPacketId(uuid);
        } else {
            //使用红包
            List<RedPacket> redPacketList = transAccount.getRedPacketList();
            List<RedPacket> filter = redPacketList.stream()
                    .filter(item -> Objects.equals(item.getRedPacketId(), transAccountLog.getRedPacketId()))
                    .collect(Collectors.toList());
            RedPacket redPacket = filter.get(0);
            redPacket.setStatus(1);
            redPacketService.update(redPacket);
            //流水值设置为红包的理论金额
            transAccountLog.setAmount(redPacket.getRedPacketAmount());
        }
        //账务流水记录
        transAccountLogMapper.insertSelective(transAccountLog);
    }

    /**
     * 积分增减及流水记录
     *
     * @param transAccountLog
     */
    private void transPoints(TransAccountLog transAccountLog, TransAccount transAccount) {
        //增加积分
        if (transAccountLog.getLogType() == 0) {
            transAccount.setBalance(transAccount.getBalance() + transAccountLog.getAmount());
            transAccount.setTotalInAmount(transAccount.getTotalInAmount() + transAccountLog.getAmount());
        } else {
            //减少积分
            transAccount.setBalance(transAccount.getBalance() - transAccountLog.getAmount());
            transAccount.setTotalOutAmount(transAccount.getTotalOutAmount() + transAccountLog.getAmount());
        }
        //账务余额表更新
        transAccountService.update(transAccount);
        //账务流水记录
        transAccountLogMapper.insertSelective(transAccountLog);
    }

    /**
     * 确认红包扣减是否满足
     *
     * @param transAccountLog
     */
    private TransAccount checkRedPacket(TransAccountLog transAccountLog) throws Exception {
        if (transAccountLog.getLogType() == null) {
            //TODO 自定义业务异常
            throw new Exception("交易类型不能为空");
        } else if (transAccountLog.getLogType() == 0) {
            //TODO 总账户阈值扣减校验
            return transAccountService.findBy("transId", transAccountLog.getTransId());
        } else if (transAccountLog.getLogType() == 1) {
            TransAccount transAccount;
            try {
                transAccount = transAccountService.findAccountWithRedPacketByTransId(transAccountLog.getTransId());
            } catch (Exception e) {
                throw new Exception("红包扣减数据错误");
            }
            List<RedPacket> redPacketList = transAccount.getRedPacketList();
            List<RedPacket> filterList = redPacketList.stream()
                    .filter(item -> Objects.equals(item.getRedPacketId(), transAccountLog.getRedPacketId()))
                    .collect(Collectors.toList());
            if (filterList.size() == 0) {
                throw new Exception("红包不存在");
            }
            RedPacket redPacket = filterList.get(0);
            //校验红包过期
            if (redPacket.getStatus() != 0) {
                throw new Exception("红包已过期或已使用");
            }
            //校验抵扣金额超标
            BigDecimal redPacketAmount = new BigDecimal(redPacket.getRedPacketAmount());
            if (redPacketAmount.compareTo(transAccountLog.getActualAmount()) < 0) {
                throw new Exception("实际抵扣金额不得超出红包上限");
            }
            return transAccount;
        } else {
            //TODO 自定义业务异常
            throw new Exception("交易类型错误");
        }
    }

    /**
     * 确认积分扣减是否满足
     *
     * @param transAccountLog
     */
    private TransAccount checkPoints(TransAccountLog transAccountLog) throws Exception {
        if (transAccountLog.getLogType() == null) {
            //TODO 自定义业务异常
            throw new Exception("交易类型不能为空");
        } else if (transAccountLog.getLogType() == 0) {
            //TODO 总账户阈值扣减校验
            Condition condition = new Condition(TransAccount.class);
            condition.createCriteria().andEqualTo("transId", transAccountLog.getTransId());
            List<TransAccount> transAccountList = transAccountService.findByCondition(condition);
            TransAccount transAccount = transAccountList.get(0);
            return transAccount;
        } else if (transAccountLog.getLogType() == 1) {
            Condition condition = new Condition(TransAccount.class);
            condition.createCriteria().andEqualTo("transId", transAccountLog.getTransId());
            List<TransAccount> transAccountList = transAccountService.findByCondition(condition);
            TransAccount transAccount = transAccountList.get(0);
            if (transAccount.getBalance() < transAccountLog.getAmount()) {
                throw new Exception("积分不足");
            }
            return transAccount;
        } else {
            //TODO 自定义业务异常
            throw new Exception("交易类型错误");
        }
    }

    /**
     * 获取startDate之后一个月的时间
     *
     * @param startDate
     * @param month
     * @return
     */
    private Date getMonthDate(Date startDate, int month) {
        LocalDateTime localDateTime = startDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime().plusMonths(month);
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
