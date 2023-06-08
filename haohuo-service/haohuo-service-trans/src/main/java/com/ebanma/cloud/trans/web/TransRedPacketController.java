package com.ebanma.cloud.trans.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.trans.model.TransRedPacket;
import com.ebanma.cloud.trans.service.TransRedPacketService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2023/06/07.
*/
@RestController
@RequestMapping("/trans/red/packet")
public class TransRedPacketController {
    @Resource
    private TransRedPacketService transRedPacketService;

    @PostMapping("/add")
    public Result add(@RequestBody TransRedPacket transRedPacket) {
        transRedPacketService.save(transRedPacket);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        transRedPacketService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody TransRedPacket transRedPacket) {
        transRedPacketService.update(transRedPacket);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TransRedPacket transRedPacket = transRedPacketService.findById(id);
        return ResultGenerator.genSuccessResult(transRedPacket);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TransRedPacket> list = transRedPacketService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
