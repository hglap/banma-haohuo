package com.ebanma.cloud.order.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.order.dao.OrderInfoMapper;
import com.ebanma.cloud.order.dao.PaymentInfoMapper;
import com.ebanma.cloud.order.model.PaymentInfo;
import com.ebanma.cloud.order.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public Result add(PaymentInfo paymentInfo) {
        return null;
    }
}
