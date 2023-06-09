package com.ebanma.cloud.order.web;

import com.ebanma.cloud.common.dto.Result;
import com.ebanma.cloud.common.dto.ResultGenerator;
import com.ebanma.cloud.order.feign.SkuInfoQueryDTO;
import com.ebanma.cloud.order.feign.countDTO;
import com.ebanma.cloud.order.model.OrderInfo;
import com.ebanma.cloud.order.model.dto.OrderInfoDTO;
import com.ebanma.cloud.order.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by CodeGenerator on 2023/06/06.
 */
@RestController
@RequestMapping("/order/info")
@CrossOrigin
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    @PostMapping("/add")
    public Result add(@RequestBody OrderInfo orderInfo) {
        orderInfoService.save(orderInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/list")
    public Result list(@RequestBody OrderInfoDTO orderInfoDTO) {
        PageHelper.startPage(orderInfoDTO.getPageNum(), orderInfoDTO.getPageSize());
        List<OrderInfoDTO> list = orderInfoService.queryAll(orderInfoDTO);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody OrderInfoDTO orderInfoDTO) {
        OrderInfoDTO orderInfoDTO1 = orderInfoService.detail(orderInfoDTO.getId());
        return ResultGenerator.genSuccessResult(orderInfoDTO1);
    }

    @PostMapping("/update")
    public Result update(@RequestBody OrderInfo orderInfo) {
        try {
            int n = orderInfoService.update(orderInfo);
            if (n == 0) {
                return ResultGenerator.genFailResult("操作失败");
            }
        } catch (Exception e) {
            return ResultGenerator.genFailResult("操作失败");
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/querySkuSaleCount")
    public Result<Map<String, countDTO>> querySkuSaleCount(@RequestBody SkuInfoQueryDTO skuInfoQueryDTO){

        return orderInfoService.querySkuSaleCount(skuInfoQueryDTO);
    }

    //@PostMapping("/add")
    //public Result add(OrderInfo orderInfo) {
    //    orderInfoService.save(orderInfo);
    //    return ResultGenerator.genSuccessResult();
    //}
    //
    //@PostMapping("/delete")
    //public Result delete(@RequestParam Integer id) {
    //    orderInfoService.deleteById(id);
    //    return ResultGenerator.genSuccessResult();
    //}
    //
    //@PostMapping("/update")
    //public Result update(OrderInfo orderInfo) {
    //    orderInfoService.update(orderInfo);
    //    return ResultGenerator.genSuccessResult();
    //}
    //
    //@PostMapping("/detail")
    //public Result detail(@RequestParam Integer id) {
    //    OrderInfo orderInfo = orderInfoService.findById(id);
    //    return ResultGenerator.genSuccessResult(orderInfo);
    //}
    //
    //@PostMapping("/list")
    //public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
    //    PageHelper.startPage(page, size);
    //  //  List<OrderInfo> list = orderInfoService.findAll();
    //    List<OrderInfo> list = orderInfoService.queryAll();
    //    PageInfo pageInfo = new PageInfo(list);
    //    return ResultGenerator.genSuccessResult(pageInfo);
    //}

    //@PostMapping("/listByCondition")
    //public Result listByCondition(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestParam String userId) {
    //    PageHelper.startPage(page, size);
    //    List<OrderInfo> list = orderInfoService.listByCondition(userId);
    //    PageInfo pageInfo = new PageInfo(list);
    //    return ResultGenerator.genSuccessResult(pageInfo);
    //}
}
