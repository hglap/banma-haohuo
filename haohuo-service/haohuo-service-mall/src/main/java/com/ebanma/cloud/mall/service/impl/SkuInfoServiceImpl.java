package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuAttachmentSearchDTO;
import com.ebanma.cloud.mall.model.dto.SkuInfoSearchDTO;
import com.ebanma.cloud.mall.model.dto.SkuRecordSearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuAttachmentRelationTypeEnum;
import com.ebanma.cloud.mall.model.enums.SkuRecordTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.model.vo.SkuRecommendVO;
import com.ebanma.cloud.mall.service.SkuAttachmentService;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.ebanma.cloud.mall.dao.SkuInfoMapper;
import com.ebanma.cloud.mall.service.SkuRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author kmkmj
* @description 针对表【sku_info】的数据库操作Service实现
* @createDate 2023-06-07 14:54:38
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoPO>
    implements SkuInfoService{

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuAttachmentService skuAttachmentService;

    @Autowired
    private SkuRecordService skuRecordService;

    @Override
    public PageInfo queryList(SkuInfoSearchDTO skuInfoSearchDTO) {

        //TODO 如果种类是精选,调用大数据接口返回idList
        List<String> idList = new ArrayList<>();


        //TODO 如果是搜索:搜索规则：1.可支持输入商品名称，商品类型进行查询（支持模糊查询）。查询结果按照符合搜索条件，以已购次数降序显示

        PageHelper.startPage(skuInfoSearchDTO.getPageNum(), skuInfoSearchDTO.getPageSize());
        List<SkuInfoPO> skuInfoPOList = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfoPO>()
                .eq(StringUtils.isNotEmpty(skuInfoSearchDTO.getCategory()),SkuInfoPO::getCategoryId, skuInfoSearchDTO.getCategory())
                .like(StringUtils.isNotEmpty(skuInfoSearchDTO.getSearchValue()),SkuInfoPO::getSkuName, skuInfoSearchDTO.getSearchValue()));
        PageInfo pageInfo = new PageInfo<>(skuInfoPOList);

        /*查询商品相册*/
        List<SkuInfoPO> pageInfoList = pageInfo.getList();
        List<SkuInfoVO> skuInfoVOList = BeanUtil.copyToList(pageInfoList, SkuInfoVO.class);
        List<String> skuInfoIdList = skuInfoVOList.stream().map(SkuInfoVO::getId).collect(Collectors.toList());
        Map<String, List<SkuAttachmentVO>> attachmentMap = skuAttachmentService.getAttachmentMap(new SkuAttachmentSearchDTO()
                .setRelationIdList(skuInfoIdList)
                .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName()));
        skuInfoVOList.forEach(skuInfo->{
            skuInfo.setAttachmentVOList(attachmentMap.get(skuInfo.getId()));
        });

        pageInfo.setList(skuInfoVOList);

        return pageInfo;
    }

    /**
     * 根据ID查询商品详情
     * @param id
     * @return
     */
    @Override
    public SkuInfoVO queryById(String id) {
        SkuInfoPO skuInfoPO = skuInfoMapper.selectById(id);
        SkuInfoVO skuInfoVO = BeanUtil.copyProperties(skuInfoPO, SkuInfoVO.class);

        //TODO 查询已卖数量【调用订单】

        /*查询热度【收藏数】*/
        Integer allCount = skuRecordService.getRecrodCountBySkuIdAndType(new SkuRecordSearchDTO()
                .setSkuId(id)
                .setType(SkuRecordTypeEnum.USER_PRODUCT_COLLECT.getName()));
        skuInfoVO.setHeat(allCount);
        //TODO 查询推荐数量【调用推荐】

        //TODO 查询当前用户是否收藏
        String UserId = "userName";
        Integer count = skuRecordService.getRecrodCountBySkuIdAndTypeAndUserId(new SkuRecordSearchDTO()
                        .setSkuId(id)
                        .setType(SkuRecordTypeEnum.USER_PRODUCT_COLLECT.getName())
                        .setUserId(UserId));
        skuInfoVO.setCollect(count > 0);
        /*查询图片*/
        List<SkuAttachmentVO> attachmentAlbumList = skuAttachmentService.getAttachmentList(new SkuAttachmentSearchDTO()
                .setRelationId(id)
                .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName()));

        skuInfoVO.setAttachmentVOList(attachmentAlbumList);

        return skuInfoVO;
    }

    /**
     * 查询推荐精选
     * @param skuInfoSearchDTO
     * @return
     */
    @Override
    public SkuRecommendVO queryRecommendList(SkuInfoSearchDTO skuInfoSearchDTO) {
        // TODO 调用推荐，获取商品对应的推荐精选帖子
        return null;
    }

    @Override
    public PageInfo searchList(SkuInfoSearchDTO skuInfoSearchDTO) {
        PageHelper.startPage(skuInfoSearchDTO.getPageNum(), skuInfoSearchDTO.getPageSize());
        List<SkuInfoPO> skuInfoPOList = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfoPO>()
                .eq(StringUtils.isNotEmpty(skuInfoSearchDTO.getCategoryId()), SkuInfoPO::getCategoryId, skuInfoSearchDTO.getCategoryId())
                .like(StringUtils.isNotEmpty(skuInfoSearchDTO.getSkuName()), SkuInfoPO::getSkuName, skuInfoSearchDTO.getSkuName())
                .like(StringUtils.isNotEmpty(skuInfoSearchDTO.getGoodsNo()), SkuInfoPO::getGoodsNo, skuInfoSearchDTO.getGoodsNo()));

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

        //TODO 调用订单查询【商品销量】

        pageInfo.setList(skuInfoVOList);
        return pageInfo;
    }
}




