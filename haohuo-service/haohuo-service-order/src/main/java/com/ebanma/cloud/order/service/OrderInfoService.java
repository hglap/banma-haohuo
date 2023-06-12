package com.ebanma.cloud.order.service;


import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.order.feign.SkuInfoQueryDTO;
import com.ebanma.cloud.order.feign.countDTO;
import com.ebanma.cloud.order.model.DisplayOrder;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface OrderInfoService {


    List<OrderInfoDTO> queryAll(OrderInfoDTO orderInfoDTO);

    OrderInfoDTO detail(Long orderId);

    int update(OrderInfo orderInfo);

    Result<OrderInfo> save(OrderInfo orderInfo);

    Result<Map<String, countDTO>> querySkuSaleCount(SkuInfoQueryDTO skuInfoQueryDTO);

    Result<DisplayOrder> getDisplayInfo(String skuId);
}
