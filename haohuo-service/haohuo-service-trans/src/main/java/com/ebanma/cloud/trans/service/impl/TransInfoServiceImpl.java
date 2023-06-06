package com.ebanma.cloud.trans.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.trans.dao.TransInfoMapper;
import com.ebanma.cloud.trans.model.TransInfo;
import com.ebanma.cloud.trans.service.TransInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class TransInfoServiceImpl extends AbstractService<TransInfo> implements TransInfoService {
    @Resource
    private TransInfoMapper transInfoMapper;

}
