package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.mall.model.dto.*;
import com.ebanma.cloud.mall.model.enums.SkuAttachmentRelationTypeEnum;
import com.ebanma.cloud.mall.model.enums.SkuRecordTypeEnum;
import com.ebanma.cloud.mall.model.enums.SkuUseStatusTypeEnum;
import com.ebanma.cloud.mall.model.po.*;
import com.ebanma.cloud.mall.model.vo.SkuAttachmentVO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.model.vo.SkuRecommendVO;
import com.ebanma.cloud.mall.service.SkuAttachmentService;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.ebanma.cloud.mall.dao.SkuInfoMapper;
import com.ebanma.cloud.mall.service.SkuInventoryService;
import com.ebanma.cloud.mall.service.SkuRecordService;
import com.ebanma.cloud.order.api.dto.SkuInfoQueryDTO;
import com.ebanma.cloud.order.api.dto.countDTO;
import com.ebanma.cloud.order.api.openfeign.OrderInfoServiceFeign;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author kmkmj
* @description 针对表【sku_info】的数据库操作Service实现
* @createDate 2023-06-07 14:54:38
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfoPO>
    implements SkuInfoService{

    private Logger logger = LoggerFactory.getLogger(SkuInfoServiceImpl.class);

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuAttachmentService skuAttachmentService;

    @Autowired
    private SkuAttachmentServiceImpl skuAttachmentServiceImpl;

    @Autowired
    private SkuRecordService skuRecordService;

    @Autowired
    private SkuRecordServiceImpl skuRecordServiceImpl;

    @Autowired
    private SkuDetailServiceImpl skuDetailServiceImpl;

    @Autowired
    private SkuInventoryService skuInventoryService;

    @Autowired
    private SkuInventoryServiceImpl skuInventoryServiceImpl;

    @Autowired
    private OrderInfoServiceFeign orderInfoServiceFeign;

    @Autowired
    private ElasticsearchRepository elasticsearchRepository;

    /**
     * 查询商品列表
     * @param skuInfoSearchDTO
     * @return
     */
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




        List<SkuInfoPO> pageInfoList = pageInfo.getList();
        List<SkuInfoVO> skuInfoVOList = BeanUtil.copyToList(pageInfoList, SkuInfoVO.class);
        List<String> skuInfoIdList = skuInfoVOList.stream().map(SkuInfoVO::getId).collect(Collectors.toList());

        /*查询商品相册*/
        Map<String, List<SkuAttachmentVO>> attachmentMap = skuAttachmentService.getAttachmentMap(new SkuAttachmentSearchDTO()
                .setRelationIdList(skuInfoIdList)
                .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName()));
        skuInfoVOList.forEach(skuInfo->{
            skuInfo.setAttachmentVOList(attachmentMap.get(skuInfo.getId()));
        });

        /* 调用【订单服务】查询商品销量 */
        /* 查询已卖数量【调用订单】*/
        Map<String, countDTO> saleCountMap = getSaleCount(idList);
        skuInfoVOList.forEach(skuInfoVO -> {
            Integer skuSaleCount = Optional.ofNullable(saleCountMap)
                    .map(m -> m.get(skuInfoVO.getId()))
                    .map(countDTO::getSkuSaleCount)
                    .orElseGet(null);
            skuInfoVO.setSaledCount(skuSaleCount);
        });
        pageInfo.setList(skuInfoVOList);

        return pageInfo;
    }

    /**
     * 调用订单服务
     * 根据商品ID查询对应销量
     * @param idList
     * @return
     */
    private Map<String, countDTO> getSaleCount(List<String> idList){
        SkuInfoQueryDTO skuInfoQueryDTO = new SkuInfoQueryDTO();
        skuInfoQueryDTO.setSkuIDList(idList);
        Result<Map<String, countDTO>> mapResult = new Result<Map<String, countDTO>>();
        try{
            mapResult = orderInfoServiceFeign.querySkuSaleCount(skuInfoQueryDTO);
        }catch (Exception e){
            logger.info("调用订单服务失败,入参：{}",skuInfoQueryDTO.toString());
        }
        return mapResult.getData();
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

        /* 查询已卖数量【调用订单】*/
        List<String> idList = new ArrayList<>();
        idList.add(id);

        Map<String, countDTO> saleCountMap = getSaleCount(idList);
        Integer skuSaleCount = Optional.ofNullable(saleCountMap)
                .map(m -> m.get(id))
                .map(countDTO::getSkuSaleCount)
                .orElseGet(null);
        skuInfoVO.setSaledCount(skuSaleCount);



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

    /**
     * 分页查询
     * @param skuInfoSearchDTO
     * @return
     */
    @Override
    public PageInfo searchList(SkuInfoSearchDTO skuInfoSearchDTO) {
        PageHelper.startPage(skuInfoSearchDTO.getPageNum(), skuInfoSearchDTO.getPageSize());
        List<SkuInfoPO> skuInfoPOList = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfoPO>()
                .eq(StringUtils.isNotEmpty(skuInfoSearchDTO.getCategoryId()), SkuInfoPO::getCategoryId, skuInfoSearchDTO.getCategoryId())
                .eq(StringUtils.isNotEmpty(skuInfoSearchDTO.getUseStatus()),SkuInfoPO::getUseStatus,skuInfoSearchDTO.getUseStatus())
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

    @Transactional
    @Override
    public Boolean add(SkuInfoInsertDTO skuInfoInsertDTO) {
        //TODO 通过上下文获取商家ID
        String storeId = "1111";

        // TODO 生成商品编号 规则为商品分类首字母+商家ID+4位自增数字（例：FZ10000001）
        String goodsNo = "xxxx";

        /* 查找排序为1的图片 */
        List<SkuAttachmentInsertDTO> attachmentAlbumList = skuInfoInsertDTO.getAttachmentInfoList();
        SkuAttachmentInsertDTO skuAttachmentInsertDTO = attachmentAlbumList.stream().filter(dto -> dto.getSeq().equals("1")).findFirst().orElse(null);
        /* 插入商品信息 */
        SkuInfoPO skuInfoPO = BeanUtil.copyProperties(skuInfoInsertDTO, SkuInfoPO.class);
        skuInfoPO.setSkuDefaultImg(skuAttachmentInsertDTO.getUrl());
        save(skuInfoPO);

        /* 创建库存记录 */
        skuInventoryService.add(new SkuInventoryInsertDTO()
                .setSkuId(skuInfoPO.getId())
                .setStoreId(storeId)
                .setAllInQua(skuInfoInsertDTO.getCurrentQua()));

        /* 插入商品参数 */
        List<SkuDetailInsertDTO> skuDetailDTO = skuInfoInsertDTO.getSkuDetailDTO();
        List<SkuDetailPO> skuDetailPOList = BeanUtil.copyToList(skuDetailDTO, SkuDetailPO.class);
        skuDetailPOList.forEach(skuDetailPO -> {
            skuDetailPO.setSkuId(skuInfoPO.getId());
        });
        skuDetailServiceImpl.saveBatch(skuDetailPOList);

        /* 插入商品相册 */
        List<SkuAttachmentPO> skuAttachmentPOList = BeanUtil.copyToList(attachmentAlbumList, SkuAttachmentPO.class);
        skuAttachmentPOList.forEach(po->{
            po.setRelationId(skuInfoPO.getId())
              .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName());
        });
        skuAttachmentServiceImpl.saveBatch(skuAttachmentPOList);

        /* 插入商品详情图片 */
        SkuAttachmentInsertDTO detailPhoto = skuInfoInsertDTO.getDetailPhoto();
        SkuAttachmentPO skuAttachmentDetailPhotoPO = BeanUtil.copyProperties(detailPhoto, SkuAttachmentPO.class);
        skuAttachmentDetailPhotoPO.setRelationId(skuInfoPO.getId())
                .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_DETAIL_IMAGE.getName());
        skuAttachmentServiceImpl.save(skuAttachmentDetailPhotoPO);


        /* 插入关键词 */
        String keyWordsList = skuInfoInsertDTO.getKeyWordsList();
        String[] wordsList = StringUtils.split(keyWordsList, " ");
        List<SkuRecordInsertDTO> skuRecordInsertDTOList = new ArrayList<SkuRecordInsertDTO>();
        for (int i = 0; i < wordsList.length; i++) {
            SkuRecordInsertDTO dto = new SkuRecordInsertDTO();
            dto.setType(SkuRecordTypeEnum.PRODUCT_KEYWORDS.getName() + "-" + wordsList[i])
               .setSkuId(skuInfoPO.getId());
            skuRecordInsertDTOList.add(dto);
        }
        skuRecordService.addList(skuRecordInsertDTOList);
        return true;
    }

    /**
     * 商品信息编辑
     * @param skuInfoEditDTO
     * @return
     */
    @Override
    public Boolean edit(SkuInfoEditDTO skuInfoEditDTO) {
        //TODO 通过上下文获取商家ID
        String storeId = "1111";

        /* 查找排序为1的图片 */
        List<SkuAttachmentInsertDTO> attachmentAlbumList = skuInfoEditDTO.getAttachmentInfoList();
        SkuAttachmentInsertDTO skuAttachmentInsertDTO = null;
        if(CollectionUtil.isNotEmpty(attachmentAlbumList)){
            skuAttachmentInsertDTO = attachmentAlbumList.stream().filter(dto -> dto.getSeq().equals("1")).findFirst().orElse(null);
        }
        /* 插入商品信息 */
        SkuInfoPO skuInfoPO = BeanUtil.copyProperties(skuInfoEditDTO, SkuInfoPO.class);
        skuInfoPO.setSkuDefaultImg(skuAttachmentInsertDTO.getUrl());
        save(skuInfoPO);

        /* 更新库存信息 */
        SkuInventoryPO skuInventoryPO = skuInventoryServiceImpl.lambdaQuery().eq(SkuInventoryPO::getSkuId, skuInfoPO.getId()).one();
        skuInventoryServiceImpl.save(skuInventoryPO.setCurrentQua(skuInfoEditDTO.getCurrentQua()));

        if(CollectionUtil.isNotEmpty(skuInfoEditDTO.getSkuDetailDTO())){
            //删除原有参数
            skuRecordServiceImpl.lambdaUpdate().eq(SkuRecordPO::getSkuId, skuInfoEditDTO.getSkuId())
                    .eq(SkuRecordPO::getType, SkuRecordTypeEnum.PRODUCT_KEYWORDS)
                    .set(SkuRecordPO::getDel, "1");
            /* 插入商品参数 */
            List<SkuDetailInsertDTO> skuDetailDTO = skuInfoEditDTO.getSkuDetailDTO();
            List<SkuDetailPO> skuDetailPOList = BeanUtil.copyToList(skuDetailDTO, SkuDetailPO.class);
            skuDetailPOList.forEach(skuDetailPO -> {
                skuDetailPO.setSkuId(skuInfoPO.getId());
            });
            skuDetailServiceImpl.saveBatch(skuDetailPOList);
        }


        if(CollectionUtil.isNotEmpty(skuInfoEditDTO.getAttachmentInfoList())){
            // 删除原有商品相册
            skuAttachmentServiceImpl.lambdaUpdate().eq(SkuAttachmentPO::getRelationId, skuInfoEditDTO.getId())
                    .eq(SkuAttachmentPO::getRelationType, SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName())
                    .set(SkuAttachmentPO::getDel, "1");
            /* 插入商品相册 */
            List<SkuAttachmentPO> skuAttachmentPOList = BeanUtil.copyToList(attachmentAlbumList, SkuAttachmentPO.class);
            skuAttachmentPOList.forEach(po->{
                po.setRelationId(skuInfoPO.getId())
                        .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_ALBUM.getName());
            });
            skuAttachmentServiceImpl.saveBatch(skuAttachmentPOList);
        }

        if(skuInfoEditDTO.getDetailPhoto()!=null){
            /* 删除原有商品图片 */
            skuAttachmentServiceImpl.lambdaUpdate().eq(SkuAttachmentPO::getRelationId, skuInfoEditDTO.getId())
                    .eq(SkuAttachmentPO::getRelationType, SkuAttachmentRelationTypeEnum.PRODUCT_DETAIL_IMAGE.getName())
                    .set(SkuAttachmentPO::getDel, "1");
            /* 插入商品详情图片 */
            SkuAttachmentInsertDTO detailPhoto = skuInfoEditDTO.getDetailPhoto();
            SkuAttachmentPO skuAttachmentDetailPhotoPO = BeanUtil.copyProperties(detailPhoto, SkuAttachmentPO.class);
            skuAttachmentDetailPhotoPO.setRelationId(skuInfoPO.getId())
                    .setRelationType(SkuAttachmentRelationTypeEnum.PRODUCT_DETAIL_IMAGE.getName());
            skuAttachmentServiceImpl.save(skuAttachmentDetailPhotoPO);
        }




        if(StringUtils.isNotEmpty(skuInfoEditDTO.getKeyWordsList())){
            /* 删除原有关键词 */
            skuRecordServiceImpl.lambdaUpdate().eq(SkuRecordPO::getSkuId, skuInfoEditDTO)
                    .eq(SkuRecordPO::getType, SkuRecordTypeEnum.PRODUCT_KEYWORDS)
                    .set(SkuRecordPO::getDel, "1");
            /* 插入关键词 */
            String keyWordsList = skuInfoEditDTO.getKeyWordsList();
            String[] wordsList = StringUtils.split(keyWordsList, " ");
            List<SkuRecordInsertDTO> skuRecordInsertDTOList = new ArrayList<SkuRecordInsertDTO>();
            for (int i = 0; i < wordsList.length; i++) {
                SkuRecordInsertDTO dto = new SkuRecordInsertDTO();
                dto.setType(SkuRecordTypeEnum.PRODUCT_KEYWORDS.getName() + "-" + wordsList[i])
                        .setSkuId(skuInfoPO.getId());
                skuRecordInsertDTOList.add(dto);
            }
            skuRecordService.addList(skuRecordInsertDTOList);
        }
        return true ;
    }

    /**
     * 商品上下架
     * @param id
     * @param useStatus
     * @return
     */
    @Override
    public Boolean editStatus(String id, String useStatus) {
        SkuInfoPO skuInfoPO = new SkuInfoPO();
        skuInfoPO.setUseStatus(useStatus).setId(id);
        updateById(skuInfoPO);
        return true;
    }

    /**
     * 商品删除
     * @param id
     * @return
     */
    @Override
    public Boolean del(String id) {
        /* 查询商品当前上下架状态 */

        SkuInfoPO skuInfoPO = skuInfoMapper.selectById(id);
        if(SkuUseStatusTypeEnum.USE.getCode().equals(skuInfoPO.getUseStatus())){
            throw new MallException("该商品为上架状态，无法删除");
        }else{
            skuInfoPO.setUseStatus(SkuUseStatusTypeEnum.UN_USE.getCode());
            skuInfoMapper.updateById(skuInfoPO);
        }

        return true;
    }

    /**
     * 根据商户IdList查询商品数量
     * @param idList
     * @return
     */
    @Override
    public Map<String, Long> getSkuInfoCountByStoreIdList(List<String> idList) {
        if(CollectionUtil.isEmpty(idList)){
            return null;
        }
        List<SkuInfoPO> skuInfoPOList = lambdaQuery().in(SkuInfoPO::getStoreId, idList).list();
        Map<String, Long> map = skuInfoPOList.stream()
                .collect(Collectors.groupingBy(SkuInfoPO::getStoreId, Collectors.summingLong(SkuInfoPO::getCurrentQua)));
        return map;
    }

    /**
     * 获取所有商品的库存数
     * @return
     */
    @Override
    public Map<String, Long> getAllSkuCount() {
        List<SkuInfoPO> skuInfoPOList = list();
        Map<String, Long> countMap = skuInfoPOList.stream().collect(Collectors.toMap(SkuInfoPO::getId, SkuInfoPO::getCurrentQua));
        return countMap;
    }

    @Override
    public List<SkuInfoVO> testEs() {
        Iterable iterable = elasticsearchRepository.findAll();
        List<SkuInfoPO> list = new ArrayList<>();
        iterable.forEach(skuinfo->list.add((SkuInfoPO)skuinfo));
        List<SkuInfoVO> skuInfoVOList = BeanUtil.copyToList(list, SkuInfoVO.class);
        return skuInfoVOList;
    }

}




