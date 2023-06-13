package com.ebanma.cloud.post.dao;

import com.ebanma.cloud.post.model.po.PostInfoPO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.user.model.UserInfo;

/**
* @author banma-0163
* @description 针对表【post_info(推荐帖信息表)】的数据库操作Mapper
* @createDate 2023-06-06 19:10:57
* @Entity com.ebanma.cloud.post.model.po.PostInfo
*/
public interface PostInfoMapper extends BaseMapper<PostInfoPO> {

    UserInfo queryUser(String userIdLocal);
}




