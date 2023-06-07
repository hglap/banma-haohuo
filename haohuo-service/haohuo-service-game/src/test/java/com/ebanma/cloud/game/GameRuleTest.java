package com.ebanma.cloud.game;

import com.ebanma.cloud.game.model.GameRule;
import com.ebanma.cloud.game.model.GameUserRecord;
import com.ebanma.cloud.game.service.GameRuleService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author banma-
 * @version $ Id: GameRuleTest, v 0.1 2023/06/06 20:13 banma- Exp $
 */

@SpringBootTest(classes = GameServiceApplication.class)
@RunWith(SpringRunner.class)
public class GameRuleTest {

    @Autowired
    GameRuleService gameRuleService;

    @org.junit.Test
    public  void test1() {

        List<GameRule> all = gameRuleService.list();
        System.out.println(all);
    }

}