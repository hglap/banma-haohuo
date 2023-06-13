package com.ebanma.cloud.trans.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.trans.api.dto.UserInfoSearchVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 刘宇
 * @version $ Id: RedPacketFeign, v 0.1 2023/06/08 17:43 banma-0194 Exp $
 */
@FeignClient(value = "trans-service", path = "/trans")
public interface RedPacketFeign {
    /**
     * 通过红包主键查红包信息
     * @param id
     * @return
     */
    @GetMapping(value = "/redPacket/queryRedPacket")
    Result queryRedPacket(String id);
}