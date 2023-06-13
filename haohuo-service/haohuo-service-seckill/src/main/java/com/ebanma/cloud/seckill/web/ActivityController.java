package com.ebanma.cloud.seckill.web;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivityGetInfoVo;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.ebanma.cloud.seckill.model.vo.SeckillGit;
import com.ebanma.cloud.seckill.service.ActivityService;
import com.ebanma.cloud.seckill.service.SeckillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/seckill")
public class ActivityController {
    @Resource
    private ActivityService activityService;

    @Resource
    private SeckillService seckillService;

    @PostMapping("/searchInfo")
    public Result searchInfo(@RequestBody ActivitySearchInfoDto activitySearchInfoDto){
        return ResultGenerator.genSuccessResult(activityService.searchInfoBypage( activitySearchInfoDto));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ActivitySaveDto saveDto){
        int result = activityService.saveActivity(saveDto);
        return ResultGenerator.genSuccessResult(result);
    }

    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam String userId){
        ActivityGetInfoVo vo = activityService.getInfo(userId);
        return ResultGenerator.genSuccessResult(vo);
    }

    @GetMapping("/{path}/seckill")
    public Result seckill(@PathVariable ("path") String path,@RequestParam String userId,@RequestParam long activityId){
        SeckillGit git = activityService.seckill(path,userId,activityId);
        return ResultGenerator.genSuccessResult(git);
    }

    @GetMapping("/redisTest")
    public Result redisGet(){
        Object result = activityService.getRedisInfo("activity");
        return ResultGenerator.genSuccessResult(result);
    }

    @GetMapping("/redisTestRead")
    public Result redisTestRead(){
        return ResultGenerator.genSuccessResult(activityService.redisTestRead(""));
    }
}
