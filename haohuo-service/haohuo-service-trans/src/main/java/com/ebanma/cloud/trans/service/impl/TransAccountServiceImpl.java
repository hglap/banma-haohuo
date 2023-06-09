package com.ebanma.cloud.trans.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.TransAccountMapper;
import com.ebanma.cloud.trans.model.RedPacket;
import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.service.TransAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class TransAccountServiceImpl extends AbstractService<TransAccount> implements TransAccountService {
    @Resource
    private TransAccountMapper transAccountMapper;

    @Override
    public TransAccount findAccountWithRedPacketByTransId(String transId) {
        return transAccountMapper.findAccountWithRedPacketByTransId(transId);
    }
}
