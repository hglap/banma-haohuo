package com.ebanma.cloud.trans.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.trans.model.TransAccountLog;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface TransAccountLogService extends Service<TransAccountLog> {
    void record(TransAccountLog transAccountLog);
}
