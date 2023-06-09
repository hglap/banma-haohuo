package com.ebanma.cloud.seckill.web;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.seckill.model.dto.ActivitySaveDto;
import com.ebanma.cloud.seckill.model.dto.ActivitySearchInfoDto;
import com.ebanma.cloud.seckill.model.po.Activity;
import com.ebanma.cloud.seckill.model.vo.ActivitySearchInfoVo;
import com.ebanma.cloud.seckill.service.ActivityService;
import com.ebanma.cloud.seckill.service.SeckillService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


//    @PostMapping("/add")
//    public Result add(Activity activity) {
//        activityService.save(activity);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/delete")
//    public Result delete(@RequestParam Integer id) {
//        activityService.deleteById(id);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/update")
//    public Result update(Activity activity) {
//        activityService.update(activity);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/detail")
//    public Result detail(@RequestParam Integer id) {
//        Activity activity = activityService.findById(id);
//        return ResultGenerator.genSuccessResult(activity);
//    }
//
//    @PostMapping("/list")
//    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
//        PageHelper.startPage(page, size);
//        List<Activity> list = activityService.findAll();
//        PageInfo pageInfo = new PageInfo(list);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }


    @PostMapping("/searchInfo")
    public Result searchInfo(@RequestBody ActivitySearchInfoDto activitySearchInfoDto){
        return ResultGenerator.genSuccessResult(activityService.searchInfoBypage( activitySearchInfoDto));
    }

    @PostMapping("/save")
    public Result save(@RequestBody ActivitySaveDto saveDto){
        int result = activityService.saveActivity(saveDto);
        return ResultGenerator.genSuccessResult(result);
    }

    @GetMapping("/redisTest")
    public Result redisGet(){
        Object result = activityService.getRedisInfo("activity");
        return ResultGenerator.genSuccessResult(result);
    }
}
