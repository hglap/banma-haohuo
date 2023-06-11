package com.ebanma.cloud.user.feign;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.user.dto.TransAccountLogSearchVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 鹿胜宝
 * @version $ Id: FeignService, v 0.1 2023/06/11 15:53 banma-0193 Exp $
 */
@Component
@FeignClient(value = "trans-service")
public interface FeignService {
    @PostMapping("/trans/account/getTransInfo")
    Result getTransInfo(@RequestBody TransAccountLogSearchVO transAccountLogSearchVO);
}
