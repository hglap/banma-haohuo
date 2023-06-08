package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuInfoSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.model.vo.SkuRecommendVO;
import com.github.pagehelper.PageInfo;

/**
* @author kmkmj
* @description 针对表【sku_info】的数据库操作Service
* @createDate 2023-06-07 14:54:38
*/
public interface SkuInfoService extends IService<SkuInfoPO> {


    PageInfo queryList(SkuInfoSearchDTO skuInfoSearchDTO);

    SkuInfoVO queryById(String id);
    //TODO:获取商品推荐精选
    SkuRecommendVO queryRecommendList(SkuInfoSearchDTO skuInfoSearchDTO);

    PageInfo searchList(SkuInfoSearchDTO skuInfoSearchDTO);

}
