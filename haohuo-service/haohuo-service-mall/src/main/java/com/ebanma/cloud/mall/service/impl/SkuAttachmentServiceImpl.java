package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.po.SkuAttachmentPO;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;
import com.ebanma.cloud.mall.service.SkuAttachmentService;
import com.ebanma.cloud.mall.dao.SkuAttachmentMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author kmkmj
* @description 针对表【sku_attachment】的数据库操作Service实现
* @createDate 2023-06-07 14:57:08
*/
@Service
public class SkuAttachmentServiceImpl extends ServiceImpl<SkuAttachmentMapper, SkuAttachmentPO>
    implements SkuAttachmentService{

    @Autowired
    private SkuAttachmentMapper skuAttachmentMapper;

    /**
     * 根据关联ID和关联类型查询附件列表
     * @param skuAttachmentSearchDTO
     * @return
     */
    @Override
    public List<SkuAttachmentVO> getAttachmentList(SkuAttachmentSearchDTO skuAttachmentSearchDTO) {
        List<SkuAttachmentPO> skuAttachmentPOList = skuAttachmentMapper.selectList(new LambdaQueryWrapper<SkuAttachmentPO>()
                .eq(StringUtils.isNotEmpty(skuAttachmentSearchDTO.getRelationId()),SkuAttachmentPO::getRelationId, skuAttachmentSearchDTO.getRelationId())
                .eq(StringUtils.isNotEmpty(skuAttachmentSearchDTO.getRelationType()),SkuAttachmentPO::getRelationType, skuAttachmentSearchDTO.getRelationType()));
        List<SkuAttachmentVO> skuAttachmentVOList = BeanUtil.copyToList(skuAttachmentPOList, SkuAttachmentVO.class);
        return skuAttachmentVOList;
    }

    /**
     * 根据关联ID列表，关联类型，返回Map.
     * key为关联ID,value为列表
     * @param skuAttachmentSearchDTO
     * @return
     */
    @Override
    public Map<String, List<SkuAttachmentVO>> getAttachmentMap(SkuAttachmentSearchDTO skuAttachmentSearchDTO) {
        List<SkuAttachmentPO> skuAttachmentPOList = skuAttachmentMapper.selectList(new LambdaQueryWrapper<SkuAttachmentPO>()
                .in(CollectionUtil.isNotEmpty(skuAttachmentSearchDTO.getRelationIdList()),SkuAttachmentPO::getRelationId,skuAttachmentSearchDTO.getRelationIdList())
                .eq(SkuAttachmentPO::getRelationType, skuAttachmentSearchDTO.getRelationType()));
        List<SkuAttachmentVO> skuAttachmentVOList = BeanUtil.copyToList(skuAttachmentPOList, SkuAttachmentVO.class);
        Map<String, List<SkuAttachmentVO>> attachmentMap = skuAttachmentVOList.stream().collect(Collectors.groupingBy(SkuAttachmentVO::getRelationId));
        return attachmentMap;
    }

    /**
     * 根据id查询附件
     * @param id
     * @return
     */
    @Override
    public SkuAttachmentVO queryById(String id) {
        SkuAttachmentPO skuAttachmentPO = skuAttachmentMapper.selectById(id);
        SkuAttachmentVO skuAttachmentVO = BeanUtil.copyProperties(skuAttachmentPO, SkuAttachmentVO.class);
        return skuAttachmentVO;
    }
}




