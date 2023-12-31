package com.ebanma.cloud.order.model;


import com.ebanma.cloud.mall.api.vo.SkuInfoVO;
import com.ebanma.cloud.trans.api.dto.RedPacket;
import com.ebanma.cloud.trans.api.dto.TransAccountLogDTO;
import com.ebanma.cloud.user.api.dto.Address;
import com.ebanma.cloud.user.api.dto.UserDTO;
import lombok.Data;

import java.util.List;

@Data
public class DisplayOrder {

    private UserDTO userInfo;

    private List<TransAccountLogDTO> redPackets;

    private SkuInfoVO skuInfo;

    private AccountInfo accountInfo;

    private Address address;

}
