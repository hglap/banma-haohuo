package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuStoreInfoPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author kmkmj
* @description 针对表【sku_store_info】的数据库操作Service
* @createDate 2023-06-07 14:58:25
*/
public interface SkuStoreInfoService extends IService<SkuStoreInfoPO> {

    PageInfo searchList(SkuStoreInfoSearchDTO skuStoreInfoSearchDTO);

    Boolean add(SkuStoreInfoInsertDTO skuStoreInfoInsertDTO);

    Boolean edit(SkuStoreInfoEditDTO skuStoreInfoEditDTO);

    Boolean editStatus(String id, String useStatus);

    Boolean del(String id);
}
