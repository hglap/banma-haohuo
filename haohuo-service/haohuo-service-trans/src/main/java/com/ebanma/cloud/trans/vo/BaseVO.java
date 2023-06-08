package com.ebanma.cloud.trans.vo;

import lombok.Data;

/**
 * @author 鹿胜宝
 * @version $ Id: BaseVO, v 0.1 2023/06/07 19:58 banma-0193 Exp $
 */
@Data
public class BaseVO {
    private Integer pageNum = 1;
    private Integer pageSize = 20;
}
