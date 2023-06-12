package com.ebanma.cloud.seckill.model.dto;

import lombok.Data;

/**
 * @author 崔国垲
 * @version $ Id: BaseDto, v 0.1 2023/06/07 14:23 banma-0197 Exp $
 */
@Data
public class BaseDto {
    private int page = 1;
    private int size = 10;
}
