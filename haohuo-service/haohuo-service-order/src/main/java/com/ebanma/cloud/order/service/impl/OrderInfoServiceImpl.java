package com.ebanma.cloud.order.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.order.dao.OrderInfoMapper;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.service.OrderInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends AbstractService<OrderInfo> implements OrderInfoService {
    @Resource
    private OrderInfoMapper orderInfoMapper;

}
