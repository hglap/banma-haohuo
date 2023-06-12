package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.dao.SkuInfoMapper;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.dto.SkuInventoryEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuInventoryInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuInventorySearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuAttachmentRelationTypeEnum;
import com.ebanma.cloud.mall.model.enums.SkuInventoryTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.ebanma.cloud.mall.model.po.SkuInventoryPO;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.model.vo.SkuInventoryVO;
import com.ebanma.cloud.mall.service.SkuAttachmentService;
import com.ebanma.cloud.mall.service.SkuInventoryService;
import com.ebanma.cloud.mall.dao.SkuInventoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author kmkmj
* @description 针对表【sku_inventory(商品库存表)】的数据库操作Service实现
* @createDate 2023-06-07 14:57:52
*/
@Service
public class SkuInventoryServiceImpl extends ServiceImpl<SkuInventoryMapper, SkuInventoryPO>
    implements SkuInventoryService{

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuAttachmentService skuAttachmentService;



    /**
     * 新增库存信息
     * @param skuInventoryInsertDTO
     * @return
     */
    @Override
    public Boolean add(SkuInventoryInsertDTO skuInventoryInsertDTO) {
        SkuInventoryPO skuInventoryPO = BeanUtil.copyProperties(skuInventoryInsertDTO, SkuInventoryPO.class);
        save(skuInventoryPO);
        return true;
    }

    /**
     * 商品出入库
     * @param skuInventoryEditDTO
     * @return
     */
    @Override
    @Transactional
    public Boolean edit(SkuInventoryEditDTO skuInventoryEditDTO) {
        String productId = skuInventoryEditDTO.getId();
        Long changeQua = skuInventoryEditDTO.getCount();
        // 获取当前库存数
        SkuInfoPO skuInfoPO = skuInfoMapper.selectById(productId);
        Long qua = skuInfoPO.getCurrentQua();
        Long currentQua = SkuInventoryTypeEnum.IN.getCode().equals(skuInventoryEditDTO.getType())?qua+changeQua:qua-changeQua;
        // 更新库存表库存
        lambdaUpdate().eq(SkuInventoryPO::getSkuId,productId)
                .set(SkuInventoryPO::getCurrentQua,currentQua)
                .update();
        // 更新商品表库存
        SkuInfoPO sku = new SkuInfoPO();
        sku.setId(productId);
        sku.setCurrentQua(currentQua);
        skuInfoMapper.updateById(sku);

        return true;
    }

    /**
     * 分页查询库存列表
     * @param skuInventorySearchDTO
     * @return
     */
    @Override
    public PageInfo queryList(SkuInventorySearchDTO skuInventorySearchDTO) {

        //TODO 获取当前商家登录人，查询当前商家的商品

        List<Long> currentQua = skuInventorySearchDTO.getCurrentQua();
        Long firstQua = 0L;
        Long lastQua = 0L;
        if(CollectionUtil.isNotEmpty(currentQua)){
            firstQua=currentQua.get(0);
            lastQua = currentQua.get(1);
        }

        PageHelper.startPage(skuInventorySearchDTO.getPageNum(), skuInventorySearchDTO.getPageSize());
        List<SkuInfoPO> skuInfoPOList = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfoPO>()
                .between(CollectionUtil.isNotEmpty(currentQua),SkuInfoPO::getCurrentQua,firstQua,lastQua)
                .like(StringUtils.isNotEmpty(skuInventorySearchDTO.getSkuName()), SkuInfoPO::getSkuName, skuInventorySearchDTO.getSkuName())
                .like(StringUtils.isNotEmpty(skuInventorySearchDTO.getGoodsNo()), SkuInfoPO::getGoodsNo, skuInventorySearchDTO.getGoodsNo()));

        PageInfo pageInfo = new PageInfo<>(skuInfoPOList);
        List<SkuInfoPO> list = pageInfo.getList();
        List<SkuInfoVO> skuInfoVOList = BeanUtil.copyToList(list, SkuInfoVO.class);
        List<String> idList = skuInfoVOList.stream().map(SkuInfoVO::getId).collect(Collectors.toList());

        /*查询商品图片*/
        Map<String, List<SkuAttachmentVO>> attachmentMap = skuAttachmentService.getAttachmentMap(new SkuAttachmentSearchDTO()
                .setRelationIdList(idList)
                .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName()));
        skuInfoVOList.forEach(skuInfoVO -> {
            skuInfoVO.setAttachmentVOList(attachmentMap.get(skuInfoVO.getId()));
        });

        List<SkuInventoryVO> skuInventoryVOList = BeanUtil.copyToList(skuInfoVOList, SkuInventoryVO.class);
        pageInfo.setList(skuInventoryVOList);

        return pageInfo;
    }
}




