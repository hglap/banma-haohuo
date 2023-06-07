package com.ebanma.cloud.trans.web;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.service.TransAccountLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/trans/account/log")
public class TransAccountLogController {
    @Resource
    private TransAccountLogService transAccountLogService;

    /**
     * 账务流水记录，增加及扣减 积分及红包 均走此接口
     * @param transAccountLog
     * @return
     */
    @PostMapping("/add")
    public Result add(TransAccountLog transAccountLog) {
        transAccountLogService.record(transAccountLog);
//        transAccountLogService.save(transAccountLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        transAccountLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TransAccountLog transAccountLog) {
        transAccountLogService.update(transAccountLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TransAccountLog transAccountLog = transAccountLogService.findById(id);
        return ResultGenerator.genSuccessResult(transAccountLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TransAccountLog> list = transAccountLogService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
