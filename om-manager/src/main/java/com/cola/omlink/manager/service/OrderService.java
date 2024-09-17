package com.cola.omlink.manager.service;

import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.order.Order;
import com.github.pagehelper.PageInfo;

public interface OrderService {

    // 生成订单
    Long submitOrder(OrderDto orderDto);

    // 获取订单分页列表
    PageInfo<Order> findOrderByPage(Integer page, Integer limit, Integer orderStatus);

    // 订单完成
    void missionComplete(Long orderId);

    // 订单完成确认
    void completeOrder(Long orderId);

    // 订单取消
    void cancelOrder(Long orderId);
}
