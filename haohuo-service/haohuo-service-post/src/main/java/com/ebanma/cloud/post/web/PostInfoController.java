package com.ebanma.cloud.post.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.post.model.dto.ImgDto;
import com.ebanma.cloud.post.model.dto.PostSearchDto;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.vo.PostInfoSearchVO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.service.PostInfoService;
import com.github.pagehelper.PageHelper;
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

    /**
     * 添加贴子
     *
     * @param postInfo 发布信息
     * @return {@link Result}
     */
    @PostMapping("/insert")
    public Result add(@RequestBody PostInfoVO postInfo) {
        Long id = postInfoService.add(postInfo);
        return ResultGenerator.genSuccessResult(id);
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

    /**
     * 上传所有图片
     *
     * @param file 文件
     * @return {@link Result}
     * @throws IOException ioexception
     */
    @PostMapping("/uploadAll")
    public Result uploadAll(MultipartFile[] file) throws IOException {
        String[] img=postInfoService.uploadAll(file);
        return ResultGenerator.genSuccessResult(img);
    }

    /**
     * 删除img
     * 上传所有图片
     *
     * @param imgDto img dto
     * @return {@link Result}
     * @throws IOException ioexception
     */
    @PostMapping("/removeImg")
    public Result removeImg(ImgDto imgDto) throws IOException {
        boolean flag = postInfoService.removeImg(imgDto);
        return ResultGenerator.genSuccessResult(flag);
    }

    /**
     * 推荐主页帖子展示
     *
     * @return {@link Result}
     */
    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, Long userId) {
        List<PostInfoVO> pageInfoList=postInfoService.getList(page, size, userId);
        return ResultGenerator.genSuccessResult(pageInfoList);
    }

    /**
     * 推荐主页帖子展示
     *
     * @return {@link Result}
     */
    @PostMapping("/search")
    public Result search(PostSearchDto postSearchDto) {
        PageHelper.startPage(postSearchDto.getPageNum(),postSearchDto.getPageSize());
        List<PostInfoVO> list = postInfoService.search(postSearchDto);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }




}
