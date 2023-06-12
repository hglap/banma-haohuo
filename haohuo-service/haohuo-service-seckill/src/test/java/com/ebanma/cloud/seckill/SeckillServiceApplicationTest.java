package com.ebanma.cloud.seckill;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.common.Application;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.service.ActivityService;
import com.ebanma.cloud.seckill.service.impl.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 崔国垲
 * @version $ Id: ActivityTest, v 0.1 2023/06/06 20:25 banma-0197 Exp $
 */
@SpringBootTest(classes = {SeckillServiceApplication.class, Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SeckillServiceApplicationTest {

//
//    @
//    private ActivityService activityService;

    @Test
    public void testActivity() throws Exception {
        ActivityService activityService = new ActivityServiceImpl();
        Activity all = activityService.findById(1);
        System.out.println(JSON.toJSONString(all,true));
    }
}
