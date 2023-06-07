package com.ebanma.cloud.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.game.dao.GameUserRecordMapper;
import com.ebanma.cloud.game.model.po.GameUserRecord;
import com.ebanma.cloud.game.service.GameUserRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserRecordServiceImpl extends ServiceImpl< GameUserRecordMapper ,GameUserRecord> implements GameUserRecordService {
    @Resource
    private GameUserRecordMapper gameUserRecordMapper;

}
