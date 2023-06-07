package com.ebanma.cloud.game.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ebanma.cloud.game.dao.GameUserPropMapper;
import com.ebanma.cloud.game.model.GameUserInfo;
import com.ebanma.cloud.game.model.GameUserProp;
import com.ebanma.cloud.game.service.GameUserPropService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Service
@Transactional
public class GameUserPropServiceImpl extends ServiceImpl<BaseMapper<GameUserProp>,GameUserProp> implements GameUserPropService {
    @Resource
    private GameUserPropMapper gameUserPropMapper;


    @Override
    public List<GameUserProp> selectByUserId(String userId) {
        return null;
    }
}
