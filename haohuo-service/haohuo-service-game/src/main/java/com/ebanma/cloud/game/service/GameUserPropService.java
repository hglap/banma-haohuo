package com.ebanma.cloud.game.service;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.game.model.po.GameUserProp;

import java.util.List;


/**
 * Created by CodeGenerator on 2023/06/06.
 */
public interface GameUserPropService extends Service<GameUserProp> {

    List<GameUserProp> selectByUserId(String userId);

}
