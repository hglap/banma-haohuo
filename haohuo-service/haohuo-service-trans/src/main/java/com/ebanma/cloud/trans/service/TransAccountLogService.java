package com.ebanma.cloud.trans.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.trans.vo.TransAccountLogVO;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface TransAccountLogService extends Service<TransAccountLog> {
    void record(TransAccountLog transAccountLog) throws Exception;

    TransAccountLogVO searchByCondition(TransAccountLogSearchVO transAccountLogSearchVO) throws Exception;
}
