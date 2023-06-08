package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.po.SkuDetailPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.mall.model.vo.SkuDetailVO;

import java.util.List;

/**
* @author kmkmj
* @description 针对表【sku_detail(商品信息表)】的数据库操作Service
* @createDate 2023-06-07 14:55:47
*/
public interface SkuDetailService extends IService<SkuDetailPO> {

    List<SkuDetailVO> queryDetailBySkuId(String id);
}
