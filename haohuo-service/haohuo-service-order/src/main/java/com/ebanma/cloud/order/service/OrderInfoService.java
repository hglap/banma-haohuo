package com.ebanma.cloud.order.service;


import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.order.model.OrderDetail;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;

import java.util.List;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface OrderInfoService {


    List<OrderInfoDTO> queryAll(OrderInfoDTO orderInfoDTO);

    OrderInfoDTO detail(Long orderId);
}
