package com.ebanma.cloud.game.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ebanma.cloud.game.model.GameUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameUserInfoMapper extends BaseMapper<GameUserInfo> {
}