package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.po.PostLikePO;
import com.ebanma.cloud.post.service.PostLikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 杜雨萌
 * @version $ Id: PostLikeController, v 0.1 2023/06/06 20:15 banma-0163 Exp $
 */
@RestController
@RequestMapping("/post/like")
public class PostLikeController {

    @Resource
    private PostLikeService postLikeService;

    @PostMapping("/add")
    public Result add(PostLikePO postLike) {
        postLikeService.save(postLike);
        return ResultGenerator.genSuccessResult();
    }

}
