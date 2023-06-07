package com.ebanma.cloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.mall.model.po.SkuDetailPO;
import com.ebanma.cloud.mall.model.vo.SkuDetailVO;
import com.ebanma.cloud.mall.service.SkuDetailService;
import com.ebanma.cloud.mall.dao.SkuDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author kmkmj
* @description 针对表【sku_detail(商品信息表)】的数据库操作Service实现
* @createDate 2023-06-07 14:55:47
*/
@Service
public class SkuDetailServiceImpl extends ServiceImpl<SkuDetailMapper, SkuDetailPO>
    implements SkuDetailService{

    @Autowired
    private SkuDetailMapper skuDetailMapper;

    @Override
    public List<SkuDetailVO> queryDetailBySkuId(String id) {
        List<SkuDetailPO> skuDetailPOList = skuDetailMapper.selectList(new LambdaQueryWrapper<SkuDetailPO>()
                .eq(SkuDetailPO::getSkuId, id));
        return BeanUtil.copyToList(skuDetailPOList, SkuDetailVO.class);
    }
}




