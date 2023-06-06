package com.ebanma.cloud.post.service.impl;


import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.post.dao.PostInfoMapper;
import com.ebanma.cloud.post.model.PostInfo;
import com.ebanma.cloud.post.service.PostInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class PostInfoServiceImpl extends AbstractService<PostInfo> implements PostInfoService {
    @Resource
    private PostInfoMapper postInfoMapper;

}
