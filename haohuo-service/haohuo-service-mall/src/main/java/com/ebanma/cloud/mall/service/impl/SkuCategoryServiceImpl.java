package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuCategoryEditDTO;
import com.ebanma.cloud.mall.model.dto.SkuCategoryInsertDTO;
import com.ebanma.cloud.mall.model.dto.SkuCategorySearchDTO;
import com.ebanma.cloud.mall.model.enums.SkuUseStatusTypeEnum;
import com.ebanma.cloud.mall.model.po.SkuCategoryPO;
import com.ebanma.cloud.mall.model.vo.SkuCategoryVO;
import com.ebanma.cloud.mall.service.SkuCategoryService;
import com.ebanma.cloud.mall.dao.SkuCategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 分页查询分类列表
     * @param skuCategorySearchDTO
     * @return
     */
    @Override
    public PageInfo queryList(SkuCategorySearchDTO skuCategorySearchDTO) {
        PageHelper.startPage(skuCategorySearchDTO.getPageNum(),skuCategorySearchDTO.getPageSize());
        List<SkuCategoryPO> skuCategoryPOList = lambdaQuery().like(StringUtils.isNotEmpty(skuCategorySearchDTO.getName()), SkuCategoryPO::getName, skuCategorySearchDTO.getName())
                .like(StringUtils.isNotEmpty(skuCategorySearchDTO.getCategoryNo()), SkuCategoryPO::getCategoryNo, skuCategorySearchDTO.getCategoryNo())
                .list();
        List<SkuCategoryVO> skuCategoryVOList = BeanUtil.copyToList(skuCategoryPOList, SkuCategoryVO.class);
        PageInfo pageInfo = new PageInfo(skuCategoryVOList);

        return pageInfo;
    }

    /**
     * 分类新增
     * @param skuCategoryInsertDTO
     * @return
     */
    @Override
    public Boolean add(SkuCategoryInsertDTO skuCategoryInsertDTO) {
        SkuCategoryPO skuCategoryPO = BeanUtil.copyProperties(skuCategoryInsertDTO, SkuCategoryPO.class);
        boolean result = save(skuCategoryPO);
        return result;
    }

    /**
     * 分类编辑
     * @param skuCategoryEditDTO
     * @return
     */
    @Override
    public Boolean edit(SkuCategoryEditDTO skuCategoryEditDTO) {
        SkuCategoryPO skuCategoryPO = BeanUtil.copyProperties(skuCategoryEditDTO, SkuCategoryPO.class);
        boolean result = updateById(skuCategoryPO);
        return result;
    }

    /**
     * 分类删除
     * @param id
     * @return
     */
    @Override
    public Boolean del(String id) {
        boolean result = removeById(id);
        return result;
    }

    /**
     * 分类修改导航栏状态
     * @param id
     * @param useStatus
     * @return
     */
    @Override
    public Boolean editStatus(String id, String useStatus) {
        boolean result = lambdaUpdate().eq(SkuCategoryPO::getId, id)
                .set(SkuCategoryPO::getUseStatus, useStatus)
                .update();
        return result;
    }

    /**
     * 分类获取可用列表
     * @return
     */
    @Override
    public List<SkuCategoryVO> getUseList() {
        List<SkuCategoryPO> skuCategoryPOList = lambdaQuery().eq(SkuCategoryPO::getUseStatus, SkuUseStatusTypeEnum.USE.getCode())
                .orderByAsc(SkuCategoryPO::getSeq)
                .list();
        List<SkuCategoryVO> skuCategoryVOList = BeanUtil.copyToList(skuCategoryPOList, SkuCategoryVO.class);
        return skuCategoryVOList;
    }
}




