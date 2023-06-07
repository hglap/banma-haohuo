package com.ebanma.cloud.game.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ebanma.cloud.game.model.GameUserProp;

import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameUserPropService extends IService<GameUserProp> {

    List<GameUserProp> selectByUserId(String userId);
}
