package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuInfoEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.model.vo.SkuRecommendVO;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    Boolean add(SkuInfoInsertDTO skuInfoInsertDTO);

    Boolean edit(SkuInfoEditDTO skuInfoEditDTO);

    Boolean editStatus(String id, String useStatus);

    Boolean del(String id);

    /**
     * 根据商户IdList查询商品数量
     * @param idList
     * @return
     */
    Map<String, Long> getSkuInfoCountByStoreIdList(List<String> idList);


    Map<String, Long> getAllSkuCount();

    List<SkuInfoVO> testEs();
}
