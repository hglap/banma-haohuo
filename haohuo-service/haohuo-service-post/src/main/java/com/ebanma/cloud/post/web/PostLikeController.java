package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.po.PostLikePO;
import com.ebanma.cloud.post.service.PostLikeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/post/like")
public class PostLikeController {
    @Resource
    private PostLikeService postLikeService;

    /**
     * 帖子点赞
     *
     * @param postLike 后像
     * @return {@link Result}
     */
    @PostMapping("/add")
    public Result add(@RequestBody PostLikePO postLike) {
        boolean flag = postLikeService.add(postLike);
        return ResultGenerator.genSuccessResult(flag);
    }

    /**
     * 删除
     * 取消点赞
     *
     * @param postLike 后像
     * @return {@link Result}
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody PostLikePO postLike) {
        boolean flag = postLikeService.removeByCondition(postLike);
        return ResultGenerator.genSuccessResult(flag);
    }

    @PostMapping("/update")
    public Result update(PostLikePO postLike) {
        postLikeService.updateById(postLike);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PostLikePO postLike = postLikeService.getById(id);
        return ResultGenerator.genSuccessResult(postLike);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PostLikePO> list = postLikeService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
