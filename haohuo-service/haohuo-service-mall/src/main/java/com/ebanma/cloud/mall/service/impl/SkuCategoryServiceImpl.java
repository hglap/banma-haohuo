package com.ebanma.cloud.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.po.SkuCategoryPO;
import com.ebanma.cloud.mall.service.SkuCategoryService;
import com.ebanma.cloud.mall.dao.SkuCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author kmkmj
* @description 针对表【sku_category】的数据库操作Service实现
* @createDate 2023-06-06 17:41:13
*/
@Service
public class SkuCategoryServiceImpl extends ServiceImpl<SkuCategoryMapper, SkuCategoryPO>
    implements SkuCategoryService{

    @Autowired
    private SkuCategoryMapper skuCategoryMapper;

    @Override
    public SkuCategoryPO test() {
        return skuCategoryMapper.selectById("1");
    }
}




