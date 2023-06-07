package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.PostComment;
import com.ebanma.cloud.post.service.PostCommentService;
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
@RequestMapping("/post/comment")
public class PostCommentController {
    @Resource
    private PostCommentService postCommentService;

    @PostMapping("/add")
    public Result add(PostComment postComment) {
        postCommentService.save(postComment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        postCommentService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(PostComment postComment) {
        postCommentService.updateById(postComment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PostComment postComment = postCommentService.getById(id);
        return ResultGenerator.genSuccessResult(postComment);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PostComment> list = postCommentService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
