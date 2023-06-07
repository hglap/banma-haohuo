package com.ebanma.cloud.game.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.game.dao.GameUserInfoMapper;
import com.ebanma.cloud.game.model.GameUserInfo;
import com.ebanma.cloud.game.service.GameUserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserInfoServiceImpl extends ServiceImpl<BaseMapper<GameUserInfo>,GameUserInfo> implements GameUserInfoService {
    @Resource
    private GameUserInfoMapper gameUserInfoMapper;

}
