package com.ebanma.cloud.user.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.user.dao.ProdLifetimeMapper;
import com.ebanma.cloud.user.model.ProdLifetime;
import com.ebanma.cloud.user.service.ProdLifetimeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class ProdLifetimeServiceImpl extends AbstractService<ProdLifetime> implements ProdLifetimeService {
    @Resource
    private ProdLifetimeMapper prodLifetimeMapper;

}
