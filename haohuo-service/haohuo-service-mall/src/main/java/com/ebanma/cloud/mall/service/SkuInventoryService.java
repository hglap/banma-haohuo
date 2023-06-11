package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.mall.model.dto.SkuInventoryEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuInventoryInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuInventorySearchDTO;
import com.ebanma.cloud.mall.model.po.SkuInventoryPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
* @author kmkmj
* @description 针对表【sku_inventory(商品库存表)】的数据库操作Service
* @createDate 2023-06-07 14:57:52
*/
public interface SkuInventoryService extends IService<SkuInventoryPO> {

    Boolean add(SkuInventoryInsertDTO skuInventoryInsertDTO);

    Boolean edit(SkuInventoryEditDTO skuInventoryEditDTO);

    PageInfo queryList(SkuInventorySearchDTO skuInventorySearchDTO);
}
