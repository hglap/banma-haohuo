package com.ebanma.cloud.trans.service.impl.transtrategyImpl;

import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.trans.dao.TransAccountLogMapper;
import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.service.TransAccountService;
import com.ebanma.cloud.trans.service.TransStrategy;
import com.ebanma.cloud.trans.vo.TransAccountLogDTO;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 鹿胜宝
 * @version $ Id: PointsStrategy, v 0.1 2023/06/11 13:59 banma-0193 Exp $
 */
@Slf4j
@Component("0")
public class PointsStrategy implements TransStrategy {

    @Resource
    private TransAccountService transAccountService;
    @Resource
    private TransAccountLogMapper transAccountLogMapper;

    /**
     * 积分流水记录
     *
     * @param transAccountLog
     * @throws Exception
     */
    @Override
    public void doTrans(TransAccountLog transAccountLog) throws Exception {
        TransAccount transAccount = checkPoints(transAccountLog);
        transPoints(transAccountLog, transAccount);
    }

    /**
     * 查询积分流水详情
     *
     * @param filterResult
     * @param transAccountLogSearchVO
     * @return
     */
    @Override
    public List<TransAccountLogDTO> doSearchTrans(List<TransAccountLog> filterResult, TransAccountLogSearchVO transAccountLogSearchVO) {
        List<TransAccountLogDTO> list = new ArrayList<>();
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
        return list;
    }

    /**
     * 确认积分扣减是否满足
     *
     * @param transAccountLog
     */
    private TransAccount checkPoints(TransAccountLog transAccountLog) throws Exception {
        if (transAccountLog.getLogType() == null) {
            MallException.fail("交易类型不能为空");
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
                log.error("拥有的可用积分为{}，想要使用的积分为{}", transAccount.getBalance(), transAccountLog.getAmount());
                MallException.fail("积分不足");
            }
            return transAccount;
        } else {
            log.error("积分账务流水交易类型为{}（0增加，1扣减）", transAccountLog.getLogType());
            MallException.fail("交易类型错误");
        }
        return null;
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
        transAccount.setModifiedTime(new Date());
        //账务余额表更新
        transAccountService.update(transAccount);
        //账务流水记录
        transAccountLog.setCreateOn(new Date());
        transAccountLog.setCreateBy(transAccountLog.getTransId());
        transAccountLogMapper.insertSelective(transAccountLog);
    }
}
