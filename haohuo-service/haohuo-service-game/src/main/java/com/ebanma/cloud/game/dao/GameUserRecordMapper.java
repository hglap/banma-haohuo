package com.ebanma.cloud.game.dao;

import com.ebanma.cloud.common.core.Mapper;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameTopRankVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GameUserRecordMapper extends Mapper<GameUserRecord> {
    List<GameTopRankVO> getTopRank(@Param("code") Integer code, @Param("count") int count);
}