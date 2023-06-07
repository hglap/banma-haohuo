package com.ebanma.cloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.dto.SkuInfoInsertDTO;
import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import com.ebanma.cloud.mall.service.SkuInfoService;
import com.ebanma.cloud.mall.dao.SkuInfoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PageInfo queryList(SkuInfoInsertDTO skuInfoInsertDTO) {

        //TODO 如果种类是精选,调用大数据接口返回idList
        List<String> idList = new ArrayList<>();


        //TODO 如果是搜索:搜索规则：1.可支持输入商品名称，商品类型进行查询（支持模糊查询）。查询结果按照符合搜索条件，以已购次数降序显示
        PageHelper.startPage(skuInfoInsertDTO.getPageNum(), skuInfoInsertDTO.getPageSize());
        List<SkuInfoPO> skuInfoPOList = skuInfoMapper.selectList(new LambdaQueryWrapper<SkuInfoPO>()
                .like(StringUtils.isNotEmpty(skuInfoInsertDTO.getSearchValue()),SkuInfoPO::getSkuName, skuInfoInsertDTO.getSearchValue()));
        PageInfo pageInfo = new PageInfo<>(skuInfoPOList);

        //TODO 查询图片
        List<SkuInfoPO> list = pageInfo.getList();

        return pageInfo;
    }
}




