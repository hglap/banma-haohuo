package com.ebanma.cloud.order.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.order.dao.PaymentInfoMapper;
import com.ebanma.cloud.order.model.PaymentInfo;
import com.ebanma.cloud.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class PaymentInfoServiceImpl extends AbstractService<PaymentInfo> implements PaymentInfoService {
    @Resource
    private PaymentInfoMapper paymentInfoMapper;

}
