package com.ebanma.cloud.mall.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author: why
 * @date: 2023/6/8
 * @time: 8:43
 * @description:
 */
@Data
@Accessors(chain = true)
public class SkuRecommendVO {
   private List<RecommendVO> list;

    /**
     * 推荐帖子总数
     */
   private Integer postCount;


}
