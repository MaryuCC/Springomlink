package com.cola.omlink.manager.controller;

import com.cola.omlink.manager.service.OrderService;
import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    // 接受订单
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderDto orderDto){
        Long id = orderService.submitOrder(orderDto);
        return Result.build(id, ResultCodeEnum.SUCCESS);
    }


    //TODO 发布订单

    //TODO 查看订单







}
