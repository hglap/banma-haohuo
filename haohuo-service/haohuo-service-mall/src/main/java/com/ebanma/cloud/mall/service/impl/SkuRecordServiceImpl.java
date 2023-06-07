package com.ebanma.cloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuRecordTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuRecordPO;
import com.ebanma.cloud.mall.service.SkuRecordService;
import com.ebanma.cloud.mall.dao.SkuRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author kmkmj
* @description 针对表【sku_record】的数据库操作Service实现
* @createDate 2023-06-07 14:58:13
*/
@Service
public class SkuRecordServiceImpl extends ServiceImpl<SkuRecordMapper, SkuRecordPO>
    implements SkuRecordService{

    @Autowired
    private SkuRecordMapper skuRecordMapper;
    @Override
    public Integer getRecrodCountBySkuIdAndType(SkuRecordSearchDTO skuRecordSearchDTO) {
        Integer count = skuRecordMapper.selectCount(new LambdaQueryWrapper<SkuRecordPO>()
                .eq(SkuRecordPO::getType, skuRecordSearchDTO.getType())
                .eq(SkuRecordPO::getSkuId, skuRecordSearchDTO.getSkuId()));
        return count;
    }

    @Override
    public Integer getRecrodCountBySkuIdAndTypeAndUserId(SkuRecordSearchDTO skuRecordSearchDTO) {
        Integer count = skuRecordMapper.selectCount(new LambdaQueryWrapper<SkuRecordPO>()
                .eq(SkuRecordPO::getType, skuRecordSearchDTO.getType())
                .eq(SkuRecordPO::getSkuId, skuRecordSearchDTO.getSkuId())
                .eq(SkuRecordPO::getCreateUser, skuRecordSearchDTO.getUserId()));
        return count;
    }
}




