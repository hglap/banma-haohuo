package com.ebanma.cloud.trans.VO;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 鹿胜宝
 * @version $ Id: TransAccountLogVO, v 0.1 2023/06/07 20:21 banma-0193 Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransAccountLogVO {
    /**
     * 账户Id
     */
    private String transId;
    /**
     * 总积分
     */
    private Long amountPoints;
    /**
     * 明细列表
     */
    private PageInfo<TransAccountLogDTO> logList;
}
