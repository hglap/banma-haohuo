package com.ebanma.cloud.game.service;

import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.game.model.vo.GameTopRankVO;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameUserRecordService extends Service<GameUserRecord> {


    /**
     * 获取用户参与记录
     *
     * @param gameUserRecordDto 游戏用户记录dto
     * @return
     */
    PageInfo<GameUserRecord> record(GameUserRecordDto gameUserRecordDto);

    /**
     * 中奖滚动信息
     *
     * @return {@link GameTopRankVO}
     */
    List<GameTopRankVO> getTopRank();
}
