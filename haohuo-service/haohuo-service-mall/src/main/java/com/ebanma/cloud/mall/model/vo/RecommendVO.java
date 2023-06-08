package com.ebanma.cloud.mall.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: why
 * @date: 2023/6/8
 * @time: 8:43
 * @description:
 */
@Data
@Accessors(chain = true)
public class RecommendVO {
    /**
     * 推荐id
     */
    private String id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 收藏数
     */
    private Long collectCount;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 地址
     */
    private String url;

}
