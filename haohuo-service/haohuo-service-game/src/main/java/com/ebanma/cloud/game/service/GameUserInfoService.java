package com.ebanma.cloud.game.service;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.model.vo.GameUserInfoVO;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameUserInfoService extends Service<GameUserInfo> {

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return {@link GameUserInfoVO}
     */
    GameUserInfo getUserInfo(String userId);


    /**
     * 获取抽奖界面详细信息
     *
     * @param userId 用户id
     * @return {@link GameUserInfoVO}
     */
    GameUserInfoVO details(String userId);

    /**
     * 记录
     *
     * @param gameUserRecordDto 游戏用户记录dto
     * @return {@link GameUserRecord}
     */
    PageInfo<GameUserRecord> record(GameUserRecordDto gameUserRecordDto);
}
