package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.po.SkuCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author kmkmj
* @description 针对表【sku_category】的数据库操作Service
* @createDate 2023-06-06 17:41:13
*/
public interface SkuCategoryService extends IService<SkuCategory> {

    SkuCategory test();
}
