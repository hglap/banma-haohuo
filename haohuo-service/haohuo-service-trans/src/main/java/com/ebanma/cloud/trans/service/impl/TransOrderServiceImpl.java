package com.ebanma.cloud.trans.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.TransOrderMapper;
import com.ebanma.cloud.trans.model.TransOrder;
import com.ebanma.cloud.trans.service.TransOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/09.
 */
@Service
@Transactional
public class TransOrderServiceImpl extends AbstractService<TransOrder> implements TransOrderService {
    @Resource
    private TransOrderMapper transOrderMapper;

}
