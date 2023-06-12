package com.ebanma.cloud.trans.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.trans.vo.TransAccountLogSearchVO;
import com.ebanma.cloud.trans.vo.TransAccountLogVO;
import com.ebanma.cloud.trans.model.TransAccountLog;
import com.ebanma.cloud.trans.service.TransAccountLogService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
@Slf4j
@RestController
@RequestMapping("/trans/account")
public class TransAccountLogController {
    @Resource
    private TransAccountLogService transAccountLogService;

    @PostMapping("/log/add")
    public Result add(@RequestBody TransAccountLog transAccountLog) throws Exception {
        transAccountLogService.save(transAccountLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/log/delete")
    public Result delete(@RequestParam Integer id) {
        transAccountLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/log/update")
    public Result update(@RequestBody TransAccountLog transAccountLog) {
        transAccountLogService.update(transAccountLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/log/detail")
    public Result detail(@RequestParam Integer id) {
        TransAccountLog transAccountLog = transAccountLogService.findById(id);
        return ResultGenerator.genSuccessResult(transAccountLog);
    }

    /**
     * 账务流水记录，增加及扣减 积分及红包 均走此接口
     *
     * @param transAccountLog
     * @return
     */
    @PostMapping("/updateTrans")
    public Result updateTrans(@Validated @RequestBody TransAccountLog transAccountLog) throws Exception {
        log.info("账务流水记录入参：{}",transAccountLog);
        transAccountLogService.record(transAccountLog);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 账务查询，包含红包及积分
     *
     * @param transAccountLogSearchVO
     * @return
     */
    @PostMapping("/getTransInfo")
    public Result<TransAccountLogVO> getTransInfo(@RequestBody TransAccountLogSearchVO transAccountLogSearchVO) throws Exception {
        log.info("账务查询入参：{}",transAccountLogSearchVO);
        TransAccountLogVO transAccountLogVO = transAccountLogService.searchByCondition(transAccountLogSearchVO);
        return ResultGenerator.genSuccessResult(transAccountLogVO);
    }
}
