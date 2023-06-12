package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.ServiceException;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.enums.GamePriceOrPropEnum;
import com.ebanma.cloud.common.enums.ResultCode;
import com.ebanma.cloud.game.service.TransService;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import com.ebanma.cloud.trans.api.dto.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.api.dto.TransAccountLogVO;
import com.ebanma.cloud.trans.api.openfeign.TransFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author banma-
 * @version $ Id: TransServiceImpl, v 0.1 2023/06/12 12:59 banma- Exp $
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TransServiceImpl implements TransService {


    @Resource
    private TransFeign transFeign;


    @Override
    public boolean updateTrans(TransAccountLog transAccountLog) {
        try {
            Result result = transFeign.updateTrans(transAccountLog);
            return ResultCode.SUCCESS.code() == result.getCode() ;
        }catch (Exception e) {
            log.info("调用账户域进行积分扣减失败。");
            throw new ServiceException("调用账户域进行积分扣减失败。",e);
        }
    }

    @Override
    public Long getPointInfo(String userId) {
        try {
            TransAccountLogSearchVO transAccountLogSearch = new TransAccountLogSearchVO();
            transAccountLogSearch.setUserId(userId);
            transAccountLogSearch.setBizType(GamePriceOrPropEnum.POINT.getValue());
            Result<TransAccountLogVO> transInfo = transFeign.getTransInfo(transAccountLogSearch);
            if(transInfo.getCode() == ResultCode.SUCCESS.code() ){
                return transInfo.getData().getAmountPoints();
            }else {
                log.info("调用账户域获取用户积分失败。");
                throw new ServiceException("调用账户域获取用户积分失败。");
            }
        }catch (Exception e){
            log.info("调用账户域获取用户积分失败。");
            log.info(e.getMessage());
            throw new ServiceException("调用账户域获取用户积分失败。",e);
        }
    }
}