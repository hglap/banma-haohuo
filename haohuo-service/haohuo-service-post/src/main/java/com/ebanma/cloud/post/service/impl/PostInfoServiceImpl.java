package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.dto.ImgDto;
import com.ebanma.cloud.post.model.dto.PostSearchDto;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.dao.PostInfoMapper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* @author banma-0163
* @description 针对表【post_info(推荐帖信息表)】的数据库操作Service实现
* @createDate 2023-06-06 19:10:57
*/
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfoPO>
    implements PostInfoService{

    @Resource
    private FastFileStorageClient fastFileStorageClient;

    private final String FASTDFSSERVERIMAGE = "http://124.221.94.214:8888/";

    private String SEPARATOR = ";";

    @Override
    public PostInfoVO getByPostId(Long id) {
        // Redis中取
        String redisKey = "post" + id;

        // 获取 redis数据库数据
        PostInfoPO postInfoPO = getById(id);
        if (postInfoPO == null){
            return null;
        }

        PostInfoVO postInfoVO = getVObyPO(postInfoPO);
        // todo 存入redis中
        // redisKey

        return postInfoVO;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        return uploadOneFile(multipartFile);
    }

    /**
     * 上传所有
     *
     * @param file 文件
     * @return {@link String[]}
     */
    @Override
    public String[] uploadAll(MultipartFile[] file) throws IOException {
        int fileLength = file.length;
        String[] content = new String[fileLength];
        for (int i = 0; i < content.length; i++) {
            content[i] = uploadOneFile(file[i]);
        }
        return content;
    }

    /**
     * 删除img
     *
     * @param imgDto img dto
     * @return boolean
     */
    @Override
    public boolean removeImg(ImgDto imgDto) {
        if (StringUtils.isNotEmpty(imgDto.getUrl())){

        }
        if(CollectionUtils.isEmpty(imgDto.getUrls())){

        }
        return true;
    }

    /**
     * 添加帖子
     *
     * @param postInfo 发布信息
     * @return {@link Long}
     */
    @Override
    public Long add(PostInfoVO postInfo) {
        PostInfoPO postInfoPO = new PostInfoPO();
        // 处理信息
        BeanUtils.copyProperties(postInfo,postInfoPO);

        // todo 使用中间件 存储内容
        String key = postInfo.getContent();

        postInfoPO.setContent(key);
        // 判断img是否为空
        if(postInfo.getImg().length != 0){
            String[] img = postInfo.getImg();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < img.length; i++) {
                stringBuilder.append(img[i]);
                if(i < img.length-1){
                    stringBuilder.append(SEPARATOR);
                }
            }
            postInfoPO.setImg(stringBuilder.toString());
        }
        // todo api 处理 当前用户信息
//        使用userId 获取 用户名 用户头像
        // api获取 用户 信息
        postInfoPO.setUserId(123456789L);
        postInfoPO.setNickName("姜鹏小小姜");
        postInfoPO.setHeadImg("http://pic1.win4000.com/wallpaper/c/5799c819cd9ae.jpg");
        boolean success = save(postInfoPO);
        if (success){
            return postInfoPO.getId();
        }else {
            return null;
        }
    }

    /**
     * 服务端搜索
     *
     * @param postSearchDto 职位搜索dto
     * @return {@link List}<{@link PostInfoVO}>
     */
    @Override
    public List<PostInfoVO> search(PostSearchDto postSearchDto) {


        return null;
    }

    /**
     * app端推荐
     *
     * @param page   页面
     * @param size   大小
     * @param userId 用户id
     * @return {@link List}<{@link PostInfoVO}>
     */
    @Override
    public List<PostInfoVO> getList(Integer page, Integer size, String userId) {
        // todo 向大数据服务请求 ids 列表
        Long[] mockIds= {1L,2L,3L,4L,5L,6L,7L,8L,1666285944905527297L,1666308613952577538L};
        List<Long> ids = new ArrayList<>(Arrays.asList(mockIds));
        // 查询数据
        List<PostInfoPO> postInfoPOList = lambdaQuery().in(PostInfoPO::getId, ids).list();
        // 数据处理
        List<PostInfoVO> voList = postInfoPOList.stream().map(this::getVObyPO).collect(Collectors.toList());


        return voList;
    }

    /**
     * PO 转 VO
     *
     * @param postInfoPO 发布信息阿宝
     * @return {@link PostInfoVO}
     */
    private PostInfoVO getVObyPO(PostInfoPO postInfoPO){
        PostInfoVO postInfoVO = new PostInfoVO();
        BeanUtils.copyProperties(postInfoPO, postInfoVO);
        // 拼接字符串
        postInfoVO.setImg(postInfoPO.getImg().split("SEPARATOR"));
        // 中间件 获取内容
        String key = postInfoPO.getContent();
        // todo 中间件
        String content = key;
        // 赋值
        postInfoVO.setContent(content);
        return postInfoVO;
    }


    /**
     * 上传一个文件
     *
     * @param multipartFile 多部分文件
     * @return {@link String}
     * @throws IOException ioexception
     */
    private String uploadOneFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
                FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null).getFullPath();
        if (fullPath == null) {
            return "";
        }
        return FASTDFSSERVERIMAGE+fullPath;
    }


}




