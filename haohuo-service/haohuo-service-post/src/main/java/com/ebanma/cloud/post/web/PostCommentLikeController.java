package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.po.PostCommentLikePO;
import com.ebanma.cloud.post.service.PostCommentLikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/post/comment/like")
public class PostCommentLikeController {
    @Resource
    private PostCommentLikeService postCommentLikeService;

    @PostMapping("/add")
    public Result add(PostCommentLikePO postCommentLike) {
        postCommentLikeService.save(postCommentLike);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        postCommentLikeService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(PostCommentLikePO postCommentLike) {
        postCommentLikeService.updateById(postCommentLike);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PostCommentLikePO postCommentLike = postCommentLikeService.getById(id);
        return ResultGenerator.genSuccessResult(postCommentLike);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PostCommentLikePO> list = postCommentLikeService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
