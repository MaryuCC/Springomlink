package com.cola.omlink.manager.service;

import com.cola.omlink.repository.dto.order.OrderDto;

public interface OrderService {

    // 接受订单
    Long submitOrder(OrderDto orderDto);
}
