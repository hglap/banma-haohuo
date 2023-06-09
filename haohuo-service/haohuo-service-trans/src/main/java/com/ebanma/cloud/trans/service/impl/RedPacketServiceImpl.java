package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.RedPacketMapper;
import com.ebanma.cloud.trans.model.RedPacket;
import com.ebanma.cloud.trans.service.RedPacketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class RedPacketServiceImpl extends AbstractService<RedPacket> implements RedPacketService {
    @Resource
    private RedPacketMapper redPacketMapper;

}
