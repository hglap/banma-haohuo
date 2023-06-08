package com.ebanma.cloud.game.dao;

import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.game.model.po.GameUserInfo;

public interface GameUserInfoMapper extends Mapper<GameUserInfo> {
    GameUserInfo getUserInfo(String userId);
}