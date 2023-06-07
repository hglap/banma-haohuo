package com.ebanma.cloud.mall.model.dto;

import com.ebanma.cloud.mall.model.vo.BasePageSearchVO;
import lombok.Data;

/**
 * @author: why
 * @date: 2023/6/7
 * @time: 15:31
 * @description:
 */
@Data
public class SkuInfoInsertDTO extends BasePageSearchVO {

    // 分类
    private String category;
    // 搜索值
    private String searchValue;

}
