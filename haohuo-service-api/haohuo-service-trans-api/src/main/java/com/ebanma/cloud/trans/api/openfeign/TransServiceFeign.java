package com.ebanma.cloud.trans.api.openfeign;/**
 * @author banma-
 * @date 2023/06/07
 */

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.trans.api.dto.TransAccountLogDTO;
import com.ebanma.cloud.trans.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 逯小雨
 * @version $ Id: TransServiceFeign, v 0.1 2023/06/07 20:37 banma- Exp $
 */
@FeignClient(value = "trans-service", path = "/trans")
public interface TransServiceFeign {
    @PostMapping("/account/log/getPointAmountInfo")
     Result<UserInfo> getPointAmountInfo(String userId);
    /**
     * 红包积分写入接口
     * 红包积分分类查 bizType 0 积分 1红包
     * 红包状态分类查 0 未使用; 1 已使用; 2 已过期
     * */
    @PostMapping("/account/log/updateTrans")
    public Result updateTrans(TransAccountLogDTO transAccountLogDTO);


}
