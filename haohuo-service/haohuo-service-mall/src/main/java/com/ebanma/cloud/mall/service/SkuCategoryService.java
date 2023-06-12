package com.ebanma.cloud.mall.service;

import com.ebanma.cloud.mall.model.dto.SkuCategoryEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuCategoryInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuCategorySearchDTO;
import com.ebanma.cloud.mall.model.po.SkuCategoryPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.mall.model.vo.SkuCategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
* @author kmkmj
* @description 针对表【sku_category】的数据库操作Service
* @createDate 2023-06-06 17:41:13
*/
public interface SkuCategoryService extends IService<SkuCategoryPO> {

    SkuCategoryPO test();

    PageInfo queryList(SkuCategorySearchDTO skuCategorySearchDTO);

    Boolean add(SkuCategoryInsertDTO skuCategoryInsertDTO);

    Boolean edit(SkuCategoryEditDTO skuCategoryEditDTO);

    Boolean del(String id);

    Boolean editStatus(String id, String useStatus);

    List<SkuCategoryVO> getUseList();
}
