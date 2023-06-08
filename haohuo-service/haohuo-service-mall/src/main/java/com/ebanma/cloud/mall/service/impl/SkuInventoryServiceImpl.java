package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuInventoryInsertDTO;
import com.ebanma.cloud.mall.model.po.SkuInventoryPO;
import com.ebanma.cloud.mall.service.SkuInventoryService;
import com.ebanma.cloud.mall.dao.SkuInventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author kmkmj
* @description 针对表【sku_inventory(商品库存表)】的数据库操作Service实现
* @createDate 2023-06-07 14:57:52
*/
@Service
public class SkuInventoryServiceImpl extends ServiceImpl<SkuInventoryMapper, SkuInventoryPO>
    implements SkuInventoryService{

    @Override
    public Boolean add(SkuInventoryInsertDTO skuInventoryInsertDTO) {
        SkuInventoryPO skuInventoryPO = BeanUtil.copyProperties(skuInventoryInsertDTO, SkuInventoryPO.class);
        save(skuInventoryPO);
        return true;
    }
}




