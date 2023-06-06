package com.ebanma.cloud.order.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.order.dao.OrderDetailMapper;
import com.ebanma.cloud.order.model.OrderDetail;
import com.ebanma.cloud.order.service.OrderDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class OrderDetailServiceImpl extends AbstractService<OrderDetail> implements OrderDetailService {
    @Resource
    private OrderDetailMapper orderDetailMapper;

}
