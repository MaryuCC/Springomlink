package com.cola.omlink.manager.controller;

import com.cola.omlink.manager.service.OrderService;
import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.order.Order;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    // 生成订单
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderDto orderDto){
        Long id = orderService.submitOrder(orderDto);
        return Result.build(id, ResultCodeEnum.SUCCESS);
    }


    // 获取订单分页列表
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<Order>> list(@PathVariable Integer page,
                                        @PathVariable Integer limit,
                                        @RequestParam(required = false, defaultValue = "") Integer orderStatus){
        PageInfo<Order> pageInfo = orderService.findOrderByPage(page,limit,orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    // 订单完成
    @PostMapping("auth/missionComplete")
    public Result missionComplete(@PathVariable Long orderId){
        orderService.missionComplete(orderId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 订单完成确认
    @PostMapping("auth/completeOrder")
    public Result completeOrder(@PathVariable Long orderId){
        orderService.completeOrder(orderId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    // 订单取消
    @PostMapping("auth/cancelOrder")
    public Result cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }







}
