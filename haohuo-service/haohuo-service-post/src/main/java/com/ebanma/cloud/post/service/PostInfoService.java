package com.ebanma.cloud.post.service;

import com.ebanma.cloud.post.model.dto.ImgDto;
import com.ebanma.cloud.post.model.dto.PostSearchDto;
import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.post.model.vo.PostInfoVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author banma-0163
* @description 针对表【post_info(推荐帖信息表)】的数据库操作Service
* @createDate 2023-06-06 19:10:57
*/
public interface PostInfoService extends IService<PostInfoPO> {

    PostInfoVO getByPostId(Long id);

    String upload(MultipartFile multipartFile) throws IOException;

    String[] uploadAll(MultipartFile[] file) throws IOException;

    boolean removeImg(ImgDto imgDto);

    Long add(PostInfoVO postInfo);

    List<PostInfoVO> search(PostSearchDto postSearchDto);

    List<PostInfoVO> getList(Integer page, Integer size, Long userId);
}
