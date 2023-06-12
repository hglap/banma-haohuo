package com.ebanma.cloud.trans.service.impl.transtrategyImpl;

import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.trans.dao.TransAccountLogMapper;
import com.ebanma.cloud.trans.model.RedPacket;
import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.model.TransRedPacket;
import com.ebanma.cloud.trans.service.RedPacketService;
import com.ebanma.cloud.trans.service.TransAccountService;
import com.ebanma.cloud.trans.service.TransRedPacketService;
import com.ebanma.cloud.trans.service.TransStrategy;
import com.ebanma.cloud.trans.vo.TransAccountLogDTO;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 鹿胜宝
 * @version $ Id: RedPacketStrategy, v 0.1 2023/06/11 14:00 banma-0193 Exp $
 */
@Slf4j
@Component("1")
public class RedPacketStrategy implements TransStrategy {
    @Resource
    private TransRedPacketService transRedPacketService;
    @Resource
    private RedPacketService redPacketService;
    @Resource
    private TransAccountService transAccountService;
    @Resource
    private TransAccountLogMapper transAccountLogMapper;

    /**
     * 红包流水记录
     *
     * @param transAccountLog
     * @throws Exception
     */
    @Override
    public void doTrans(TransAccountLog transAccountLog) throws Exception {
        TransAccount transAccount = checkRedPacket(transAccountLog);
        transRedPacket(transAccountLog, transAccount);
    }

    /**
     * 查询红包流水详情
     *
     * @param filterResult
     * @param transAccountLogSearchVO
     * @return
     */
    @Override
    public List<TransAccountLogDTO> doSearchTrans(List<TransAccountLog> filterResult, TransAccountLogSearchVO transAccountLogSearchVO) {
        List<TransAccountLogDTO> list = new ArrayList<>();
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
        return list;
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
     * 确认红包扣减是否满足
     *
     * @param transAccountLog
     */
    private TransAccount checkRedPacket(TransAccountLog transAccountLog) throws Exception {
        if (transAccountLog.getLogType() == null) {
            MallException.fail("交易类型不能为空");
        } else if (transAccountLog.getLogType() == 0) {
            //TODO 总账户阈值扣减校验
            return transAccountService.findBy("transId", transAccountLog.getTransId());
        } else if (transAccountLog.getLogType() == 1) {
            TransAccount transAccount = transAccountService.findAccountWithRedPacketByTransId(transAccountLog.getTransId());
            List<RedPacket> redPacketList = transAccount.getRedPacketList();
            List<RedPacket> filterList = redPacketList.stream()
                    .filter(item -> Objects.equals(item.getRedPacketId(), transAccountLog.getRedPacketId()))
                    .collect(Collectors.toList());
            if (filterList.size() == 0) {
                MallException.fail("红包不存在");
            }
            RedPacket redPacket = filterList.get(0);
            //校验红包过期
            if (redPacket.getStatus() != 0) {
                log.error("所要使用的红包状态为{}", redPacket.getStatus());
                MallException.fail("红包已过期或已使用");
            }
            //校验抵扣金额超标
            BigDecimal redPacketAmount = new BigDecimal(redPacket.getRedPacketAmount());
            if (redPacketAmount.compareTo(transAccountLog.getActualAmount()) < 0) {
                log.error("所要使用的红包额度为{}，想要抵扣的金额为{}", redPacketAmount, transAccountLog.getActualAmount());
                MallException.fail("实际抵扣金额不得超出红包上限");
            }
            return transAccount;
        } else {
            log.error("红包账务流水交易类型为{}（0增加，1扣减）", transAccountLog.getLogType());
            MallException.fail("交易类型错误");
        }
        return null;
    }

    /**
     * 红包增减及流水记录
     *
     * @param transAccountLog
     */
    private void transRedPacket(TransAccountLog transAccountLog, TransAccount transAccount) {
        //增加红包
        if (transAccountLog.getLogType() == 0) {
            //根据金额创建红包
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
        transAccountLog.setCreateOn(new Date());
        transAccountLog.setCreateBy(transAccountLog.getTransId());
        transAccountLogMapper.insertSelective(transAccountLog);
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
