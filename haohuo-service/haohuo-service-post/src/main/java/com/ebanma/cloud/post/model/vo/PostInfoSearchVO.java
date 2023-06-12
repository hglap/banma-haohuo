package com.ebanma.cloud.post.model.vo;/**
 * @author 盛鑫
 * @date 2023/06/07
 */

import lombok.Data;

/**
 * @author 盛鑫
 * @version $ Id: PostInfoSearchVO, v 0.1 2023/06/07 14:24 盛鑫 Exp $
 */
@Data
public class PostInfoSearchVO {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String userId;
}
