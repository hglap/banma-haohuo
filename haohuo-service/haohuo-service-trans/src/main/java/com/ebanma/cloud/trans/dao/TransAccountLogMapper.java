package com.ebanma.cloud.trans.dao;

import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.trans.model.TransAccountLog;

import java.util.List;

public interface TransAccountLogMapper extends Mapper<TransAccountLog> {
    List<TransAccountLog> searchLogsWithRedPacket(TransAccountLog transAccountLog);
}