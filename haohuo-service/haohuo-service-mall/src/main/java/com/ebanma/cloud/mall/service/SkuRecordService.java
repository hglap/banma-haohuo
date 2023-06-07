package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuRecordPO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author kmkmj
* @description 针对表【sku_record】的数据库操作Service
* @createDate 2023-06-07 14:58:13
*/
public interface SkuRecordService extends IService<SkuRecordPO> {

    Integer getRecrodCountBySkuIdAndType(SkuRecordSearchDTO skuRecordSearchDTO);

    Integer getRecrodCountBySkuIdAndTypeAndUserId(SkuRecordSearchDTO skuRecordSearchDTO);

    Boolean collect(String productId);
}
