package com.ebanma.cloud.trans.service;

import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.vo.TransAccountLogDTO;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;

import java.util.List;

/**
 * @author 鹿胜宝
 * @version $ Id: TransStragety, v 0.1 2023/06/11 13:58 banma-0193 Exp $
 */
public interface TransStrategy {
    void doTrans(TransAccountLog transAccountLog) throws Exception;

    List<TransAccountLogDTO> doSearchTrans(List<TransAccountLog> filterAccountLog, TransAccountLogSearchVO transAccountLogSearchVO);
}
