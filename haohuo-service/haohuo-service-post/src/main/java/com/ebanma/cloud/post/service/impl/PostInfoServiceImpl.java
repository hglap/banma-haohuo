package com.ebanma.cloud.post.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import com.ebanma.cloud.post.service.PostInfoService;
import com.ebanma.cloud.post.dao.PostInfoMapper;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

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

    @Override
    public PostInfoVO getByPostId(Long id) {
        // 获取 数据库数据
        PostInfoPO postInfoPO = getById(id);
        if (postInfoPO == null){
            return null;
        }
        PostInfoVO postInfoVO = new PostInfoVO();
        BeanUtils.copyProperties(postInfoPO, postInfoVO);
        // 拼接字符串
        postInfoVO.setImg(postInfoPO.getImg().split(";"));
        // 中间件 获取内容
        String key = postInfoPO.getContent();
        // todo
        String content = key;
        // 赋值
        postInfoVO.setContent(content);
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
     * 上传一个文件
     *
     * @param multipartFile 多部分文件
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String uploadOneFile(MultipartFile multipartFile) throws IOException {
        String fullPath = fastFileStorageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
                FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null).getFullPath();
        if (fullPath == null) {
            return "";
        }
        return FASTDFSSERVERIMAGE+fullPath;
    }


}




