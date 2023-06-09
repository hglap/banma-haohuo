package com.ebanma.cloud.trans.dao;


import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.trans.model.RedPacket;
import com.ebanma.cloud.trans.model.TransAccount;

import java.util.List;

public interface TransAccountMapper extends Mapper<TransAccount> {
    TransAccount findAccountWithRedPacketByTransId(String transId);
}