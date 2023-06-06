package com.ebanma.cloud.mall.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.mall.dao.SkuInfoMapper;
import com.ebanma.cloud.mall.model.SkuInfo;
import com.ebanma.cloud.mall.service.SkuInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class SkuInfoServiceImpl extends AbstractService<SkuInfo> implements SkuInfoService {
    @Resource
    private SkuInfoMapper skuInfoMapper;

}
