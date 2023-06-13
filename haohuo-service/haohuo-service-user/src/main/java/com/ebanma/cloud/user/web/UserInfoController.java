package com.ebanma.cloud.user.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.user.common.BaseContextHandler;
import com.ebanma.cloud.user.model.UserInfo;
import com.ebanma.cloud.user.service.UserInfoService;
import com.ebanma.cloud.user.vo.UserInfoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/userInfo/info")
public class UserInfoController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public Result add(UserInfo userInfo) {
        userInfoService.save(userInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UserInfo userInfo) {
        userInfoService.update(userInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserInfo userInfo = userInfoService.findById(id);
        return ResultGenerator.genSuccessResult(userInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserInfo> list = userInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/testMQ")
    public void testMQ() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("principalId", BaseContextHandler.getUserId());
        map.put("principalType", "sign");
        map.put("productCode", "USER");
        map.put("amount",0L);
        rocketMQTemplate.convertAndSend("prod-topic", map);

    }

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserInfo")
    public Result getUserInfo(@RequestParam String userId) {
        UserInfoVO userInfo = userInfoService.getUserInfo(userId);
        return ResultGenerator.genSuccessResult(userInfo);
    }
}
