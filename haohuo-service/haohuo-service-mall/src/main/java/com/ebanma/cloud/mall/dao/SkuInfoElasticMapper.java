package com.ebanma.cloud.mall.dao;

import com.ebanma.cloud.mall.model.po.SkuInfoPO;
import com.ebanma.cloud.mall.model.vo.SkuInfoVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: why
 * @date: 2023/6/12
 * @time: 17:18
 * @description:
 */
public interface SkuInfoElasticMapper extends ElasticsearchRepository<SkuInfoPO,String> {
}
