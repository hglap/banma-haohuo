package com.ebanma.cloud.trans.web;

import com.ebanma.cloud.common.core.Service;
import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.trans.model.TransAccount;
import com.ebanma.cloud.trans.service.TransAccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/06.
*/
@RestController
@RequestMapping("/trans/account")
public class TransAccountController {
    @Resource
    private TransAccountService transAccountService;

    @PostMapping("/add")
    public Result add(@RequestBody TransAccount transAccount) {
        transAccountService.save(transAccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        transAccountService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody TransAccount transAccount) {
        transAccountService.update(transAccount);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TransAccount transAccount = transAccountService.findById(id);
        return ResultGenerator.genSuccessResult(transAccount);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TransAccount> list = transAccountService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
