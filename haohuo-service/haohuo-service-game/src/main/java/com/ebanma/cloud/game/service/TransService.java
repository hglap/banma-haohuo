package com.ebanma.cloud.game.service;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import com.ebanma.cloud.trans.api.dto.TransAccountLogVO;

public interface TransService {

    /**
     * 账务增删，包含积分和红包
     *
     * @param transAccountLog
     * @return
     */
    boolean updateTrans(TransAccountLog transAccountLog);

    /**
     *
     * 账务查询-积分
     *
     * @param userId 用户id
     * @return {@link Result}<{@link TransAccountLogVO}>
     */
    Long getPointInfo(String userId);
}
