package com.ebanma.cloud.mall.dao;

import com.ebanma.cloud.mall.model.po.SkuDetailPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kmkmj
* @description 针对表【sku_detail(商品信息表)】的数据库操作Mapper
* @createDate 2023-06-07 14:55:47
* @Entity com.ebanma.cloud.mall.model.po.SkuDetail
*/
@Mapper
public interface SkuDetailMapper extends BaseMapper<SkuDetailPO> {

}




