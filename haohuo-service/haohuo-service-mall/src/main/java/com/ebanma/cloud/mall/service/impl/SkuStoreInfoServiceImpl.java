package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.enums.ResultCode;
import com.ebanma.cloud.common.exception.MallException;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuStoreInfoSearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuUseStatusTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuStoreInfoPO;
import com.ebanma.cloud.mall.model.vo.SkuStoreInfoVO;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.ebanma.cloud.mall.service.SkuStoreInfoService;
import com.ebanma.cloud.mall.dao.SkuStoreInfoMapper;
import com.ebanma.cloud.order.api.dto.SkuInfoQueryDTO;
import com.ebanma.cloud.order.api.dto.countDTO;
import com.ebanma.cloud.order.api.openfeign.OrderInfoServiceFeign;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author kmkmj
* @description 针对表【sku_store_info】的数据库操作Service实现
* @createDate 2023-06-07 14:58:25
*/
@Service
public class SkuStoreInfoServiceImpl extends ServiceImpl<SkuStoreInfoMapper, SkuStoreInfoPO>
    implements SkuStoreInfoService{

    private Logger logger = LoggerFactory.getLogger(SkuStoreInfoServiceImpl.class);

    @Autowired
    private SkuStoreInfoMapper skuStoreInfoMapper;

    @Autowired
    private SkuInfoService skuInfoService;


    @Autowired
    private OrderInfoServiceFeign orderInfoServiceFeign;

    /**
     * 分页查询商家列表
     * @param skuStoreInfoSearchDTO
     * @return
     */
    @Override
    public PageInfo searchList(SkuStoreInfoSearchDTO skuStoreInfoSearchDTO) {
        PageHelper.startPage(skuStoreInfoSearchDTO.getPageNum(), skuStoreInfoSearchDTO.getPageSize());
        List<String> createTime = skuStoreInfoSearchDTO.getCreateTime();
        String firstDate = new Date().toString();
        String lastDate = new Date().toString();
        if(CollectionUtil.isNotEmpty(createTime)){
            firstDate = createTime.get(0);
            lastDate = createTime.get(1);
        }
        List<SkuStoreInfoPO> skuStoreInfoPOList = lambdaQuery().like(StringUtils.isNotEmpty(skuStoreInfoSearchDTO.getAccount()), SkuStoreInfoPO::getAccount, skuStoreInfoSearchDTO.getAccount())
                .like(StringUtils.isNotEmpty(skuStoreInfoSearchDTO.getName()), SkuStoreInfoPO::getName, skuStoreInfoSearchDTO.getName())
                .eq(StringUtils.isNotEmpty(skuStoreInfoSearchDTO.getUseStatus()),SkuStoreInfoPO::getUseStatus,skuStoreInfoSearchDTO.getUseStatus())
                .between(CollectionUtil.isNotEmpty(skuStoreInfoSearchDTO.getCreateTime()), SkuStoreInfoPO::getCreateTime, firstDate, lastDate)
                .list();

        PageInfo pageInfo = new PageInfo<>(skuStoreInfoPOList);
        List pageInfoList = pageInfo.getList();
        List<SkuStoreInfoVO> skuStoreInfoVOList = BeanUtil.copyToList(pageInfoList, SkuStoreInfoVO.class);

        List<String> idList = skuStoreInfoVOList.stream().map(SkuStoreInfoVO::getId).collect(Collectors.toList());

        // 查询商家的商品数量
        Map<String, Long> skuInfoCountByStoreIdMap= skuInfoService.getSkuInfoCountByStoreIdList(idList);
        skuStoreInfoVOList.forEach(skuStoreInfoVO -> {
            skuStoreInfoVO.setProductCount(skuInfoCountByStoreIdMap.get(skuStoreInfoVO.getId()));
        });

        /* 调用【订单】查询商家的营业金额，订单数量 */
        Map<String, countDTO> storeBusinessAmountAndOrderCountByStoreIdListMap = getStoreBusinessAmountAndOrderCountByStoreIdList(idList);
        if (storeBusinessAmountAndOrderCountByStoreIdListMap != null) {
            skuStoreInfoVOList.forEach(skuStoreInfoVO -> {
                countDTO countDTO = storeBusinessAmountAndOrderCountByStoreIdListMap.get(skuStoreInfoVO.getId());
                if(countDTO != null){
                    skuStoreInfoVO.setIncome(countDTO.getAmountCount())
                            .setOrderCount(countDTO.getOrderCount());
                }
            });
        }



        pageInfo.setList(skuStoreInfoVOList);
        return pageInfo;
    }

    /**
     * 根据商户IdList查询商家营业金额,订单数量
     * @param idList
     * @return
     */
    private Map<String, countDTO> getStoreBusinessAmountAndOrderCountByStoreIdList(List<String> idList) {
        if(CollectionUtil.isEmpty(idList)){
            return null;
        }
        SkuInfoQueryDTO skuInfoQueryDTO = new SkuInfoQueryDTO();
        skuInfoQueryDTO.setMerchantIdList(idList);
        Result<Map<String, countDTO>> result = null;
        Map<String, countDTO> map = null;
        try {
            result = orderInfoServiceFeign.querySkuSaleCount(skuInfoQueryDTO);
            if(ResultCode.SUCCESS.code() == result.getCode()){
                map = result.getData();
            }
            logger.info("调用订单服务获取商家营业金额及订单数量成功！");
            logger.info("result = {}", result);
        }catch (Exception e){
            logger.info("调用订单服务获取商家营业金额及订单数量失败!");
            logger.info("商家IdList:{}",idList);
            throw new RuntimeException(e);
        }
        return map;
    }





    /**
     * 商家新增
     * @param skuStoreInfoInsertDTO
     * @return
     */
    @Override
    public Boolean add(SkuStoreInfoInsertDTO skuStoreInfoInsertDTO) {
        SkuStoreInfoPO skuStoreInfoPO = BeanUtil.copyProperties(skuStoreInfoInsertDTO, SkuStoreInfoPO.class);
        save(skuStoreInfoPO);
        return true;
    }

    /**
     * 商家编辑
     * @param skuStoreInfoEditDTO
     * @return
     */
    @Override
    public Boolean edit(SkuStoreInfoEditDTO skuStoreInfoEditDTO) {
        SkuStoreInfoPO skuStoreInfoPO = BeanUtil.copyProperties(skuStoreInfoEditDTO, SkuStoreInfoPO.class);
        boolean result = updateById(skuStoreInfoPO);
        return result;
    }

    /**
     * 商家启用
     * @param id
     * @param useStatus
     * @return
     */
    @Override
    public Boolean editStatus(String id, String useStatus) {
        boolean result = lambdaUpdate().eq(SkuStoreInfoPO::getId, id)
                .set(SkuStoreInfoPO::getUseStatus, useStatus)
                .update();
        return result;
    }

    /**
     * 商家删除
     * @param id
     * @return
     */
    @Override
    public Boolean del(String id) {
        SkuStoreInfoPO skuStoreInfoPO = getById(id);
        if(SkuUseStatusTypeEnum.USE.getCode().equals(skuStoreInfoPO.getUseStatus())){
            throw new MallException("该账户为启用状态，无法删除");
        }
        boolean result = removeById(id);
        return result;
    }
}




