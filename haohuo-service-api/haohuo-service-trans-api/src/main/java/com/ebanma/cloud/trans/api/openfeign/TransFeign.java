package com.ebanma.cloud.trans.api.openfeign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.trans.api.dto.TransAccountLog;
import com.ebanma.cloud.trans.api.dto.TransAccountLogSearchVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 鹿胜宝
 * @version $ Id: UpdateTrans, v 0.1 2023/06/08 22:48 banma-0193 Exp $
 */

@FeignClient(value = "mall-service" ,path="/trans/account")
public interface TransFeign {

    /**
     * 账务增删，包含积分和红包
     *
     * @param transAccountLog
     * @return
     */
    @PostMapping("/updateTrans")
    Result updateTrans(@RequestBody TransAccountLog transAccountLog);

    /**
     * 账务查询，包含红包和积分
     *
     * @param transAccountLogSearchVO
     * @return
     */
    @PostMapping("/getTransInfo")
    Result getTransInfo(@RequestBody TransAccountLogSearchVO transAccountLogSearchVO);
}
