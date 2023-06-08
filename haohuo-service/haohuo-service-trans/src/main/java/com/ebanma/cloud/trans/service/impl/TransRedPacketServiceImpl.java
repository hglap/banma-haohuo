package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.TransRedPacketMapper;
import com.ebanma.cloud.trans.model.TransRedPacket;
import com.ebanma.cloud.trans.service.TransRedPacketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/07.
 */
@Service
@Transactional
public class TransRedPacketServiceImpl extends AbstractService<TransRedPacket> implements TransRedPacketService {
    @Resource
    private TransRedPacketMapper transRedPacketMapper;

}
