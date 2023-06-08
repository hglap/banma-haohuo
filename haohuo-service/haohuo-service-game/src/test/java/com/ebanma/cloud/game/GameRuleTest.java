package com.ebanma.cloud.game;

import com.ebanma.cloud.game.model.po.GameRule;
import com.ebanma.cloud.game.model.po.GameUserInfo;
import com.ebanma.cloud.game.service.GameRuleService;
import com.ebanma.cloud.game.service.GameUserInfoService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.locks.Condition;

/**
 * @author banma-
 * @version $ Id: GameRuleTest, v 0.1 2023/06/06 20:13 banma- Exp $
 */

@SpringBootTest(classes = GameServiceApplication.class)
@RunWith(SpringRunner.class)
public class GameRuleTest {

    @Autowired
    private  GameRuleService gameRuleService;
    @Autowired
    private GameUserInfoService gameUserInfoService;

    @org.junit.Test
    public  void test1() {

        GameUserInfo userInfo = gameUserInfoService.findBy("userId", "100");
        System.out.println(userInfo);
    }

}