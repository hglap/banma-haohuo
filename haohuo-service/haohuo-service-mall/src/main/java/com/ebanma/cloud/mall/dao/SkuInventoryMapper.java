package com.ebanma.cloud.mall.dao;

import com.ebanma.cloud.mall.model.po.SkuInventoryPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kmkmj
* @description 针对表【sku_inventory(商品库存表)】的数据库操作Mapper
* @createDate 2023-06-07 14:57:52
* @Entity com.ebanma.cloud.mall.model.po.SkuInventory
*/
@Mapper
public interface SkuInventoryMapper extends BaseMapper<SkuInventoryPO> {

}




