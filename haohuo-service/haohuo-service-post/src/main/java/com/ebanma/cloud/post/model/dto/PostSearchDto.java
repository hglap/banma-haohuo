package com.ebanma.cloud.post.model.dto;


import lombok.Data;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 职位搜索dto
 *
 * @author banma-柳成冬
 * @date 2023/06/07
 */
@Data
public class PostSearchDto {

    /**
     * 作者id
     */
    private String author;

    //todo 确认时间格式
    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;


    /**
     * 页面num
     */
    private Integer pageNum;

    /**
     * 页面大小
     */
    private Integer pageSize;
    /**
     * 标题
     */
    private String title;

    private String[] timeRange;

}
