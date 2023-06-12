package com.ebanma.cloud.game.dao;

import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.vo.GameRankingListVO;
import com.ebanma.cloud.game.model.vo.GameRankingVO;

import java.util.List;

public interface GameUserInfoMapper extends Mapper<GameUserInfo> {
    GameUserInfo getUserInfo(String userId);

    GameRankingVO getRanking();

    List<GameRankingListVO> getRankingList();
}