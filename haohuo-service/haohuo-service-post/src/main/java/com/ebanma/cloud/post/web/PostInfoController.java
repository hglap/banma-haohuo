package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.vo.PostInfoSearchVO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.service.PostInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/post/info")
public class PostInfoController {
    @Resource
    private PostInfoService postInfoService;

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

    @PostMapping("/update")
    public Result update(PostInfoPO postInfo) {
        postInfoService.updateById(postInfo);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 获取推荐帖子
     *
     * @param postInfoVO 发布信息签证官
     * @return {@link Result}
     */
    @PostMapping("/detail")
    public Result detail(@RequestBody PostInfoVO postInfoVO) {
        PostInfoVO postInfo = postInfoService.getByPostId(postInfoVO.getId());
        return ResultGenerator.genSuccessResult(postInfo);
    }

    /**
     * 上传
     *
     * @param file 文件
     * @return {@link Result}
     * @throws IOException ioexception
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String img=postInfoService.upload(file);
        return ResultGenerator.genSuccessResult(img);
    }

    @PostMapping("/uploadAll")
    public Result upload(MultipartFile[] file) throws IOException {
        String[] img=postInfoService.uploadAll(file);
        return ResultGenerator.genSuccessResult(img);
    }

    /**
     * 向后端返回list
     *
     * @return {@link Result}
     */
    @PostMapping("/list")
    public Result list(@RequestBody PostInfoSearchVO searchVO) {
        List<PostInfoPO> list = postInfoService.list();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
