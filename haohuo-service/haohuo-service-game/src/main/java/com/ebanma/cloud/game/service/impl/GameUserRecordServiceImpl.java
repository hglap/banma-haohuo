package com.ebanma.cloud.game.service.impl;

import com.ebanma.cloud.common.core.AbstractService;
import com.ebanma.cloud.game.dao.GameUserRecordMapper;
import com.ebanma.cloud.game.model.dto.GameUserRecordDto;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.service.GameUserRecordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserRecordServiceImpl extends AbstractService<GameUserRecord> implements GameUserRecordService {
    @Resource
    private GameUserRecordMapper gameUserRecordMapper;

    @Override
    public PageInfo<GameUserRecord> record(GameUserRecordDto gameUserRecordDto) {
        PageHelper.startPage(gameUserRecordDto.getPageNum(),gameUserRecordDto.getPageSize(),"create_time");
        GameUserRecord gameUserRecord = new GameUserRecord();
        gameUserRecord.setUserId(gameUserRecordDto.getUserId());
        List<GameUserRecord> records = gameUserRecordMapper.select(gameUserRecord);
        return new PageInfo<GameUserRecord>(records);
    }
}
