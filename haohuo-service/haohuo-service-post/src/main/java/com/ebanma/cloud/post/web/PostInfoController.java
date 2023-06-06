package com.ebanma.cloud.post.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.po.PostLikePO;
import com.ebanma.cloud.post.service.PostInfoService;
import com.ebanma.cloud.post.service.PostLikeService;
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
@RequestMapping("/post/info")
public class PostInfoController {
    @Resource
    private PostInfoService postInfoService;

    @Resource
    private PostLikeService postLikeService;

    @PostMapping("/add")
    public Result add(PostInfoPO postInfo) {
        postInfoService.save(postInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        postInfoService.removeById(id);
        return ResultGenerator.genSuccessResult();
    }

//    @PostMapping("/update")
//    public Result update(PostInfo postInfo) {
//        postInfoService.update(postInfo);
//        return ResultGenerator.genSuccessResult();
//    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        PostInfoPO postInfo = postInfoService.getById(id);
        return ResultGenerator.genSuccessResult(postInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PostInfoPO> list = postInfoService.list();
        QueryWrapper<PostLikePO> queryWrapper = new QueryWrapper<>();
        // redis取出当前用户

//        queryWrapper.eq(PostLikePO::getUserId,1);
        list.stream().forEach(postInfo -> {

        });
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
