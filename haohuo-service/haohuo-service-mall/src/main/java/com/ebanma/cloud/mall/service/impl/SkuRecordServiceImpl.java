package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuRecordInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuRecordTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuRecordPO;
import com.ebanma.cloud.mall.service.SkuRecordService;
import com.ebanma.cloud.mall.dao.SkuRecordMapper;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 商品一键收藏
     * @param productId
     * @return
     */
    @Override
    public Boolean collect(String productId) {
        //TODO:更换成上下文的用户ID
        String userId = "userId";
        SkuRecordPO skuRecordPO = skuRecordMapper.selectOne(new LambdaQueryWrapper<SkuRecordPO>()
                .eq(SkuRecordPO::getSkuId, productId)
                .eq(SkuRecordPO::getType, SkuRecordTypeEnum.USER_PRODUCT_COLLECT.getName())
                .eq(SkuRecordPO::getCreateUser, userId));

        if (skuRecordPO != null) {
            skuRecordMapper.deleteById(skuRecordPO);
        }

        skuRecordMapper.insert(new SkuRecordPO()
                .setSkuId(productId)
                .setType(SkuRecordTypeEnum.USER_PRODUCT_COLLECT.getName()));

        return true;
    }

    /**
     * 插入记录
     * @param recordInsertDTO
     * @return
     */
    @Override
    public Boolean add(SkuRecordInsertDTO recordInsertDTO) {
        SkuRecordPO skuRecordPO = BeanUtil.copyProperties(recordInsertDTO, SkuRecordPO.class);
        skuRecordMapper.insert(skuRecordPO);
        return true;
    }

    /**
     * 批量插入记录
     * @param skuRecordInsertDTOList
     * @return
     */
    @Override
    public Boolean addList(List<SkuRecordInsertDTO> skuRecordInsertDTOList) {
        List<SkuRecordPO> skuRecordPOList = BeanUtil.copyToList(skuRecordInsertDTOList, SkuRecordPO.class);
        saveBatch(skuRecordPOList);
        return true;
    }
}




