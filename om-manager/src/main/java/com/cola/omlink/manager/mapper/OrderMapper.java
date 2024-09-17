package com.cola.omlink.manager.mapper;


import com.cola.omlink.repository.entity.order.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    void save(Order order);

    // 根据userId 和 订单状态查询
    List<Order> findUserPage(Long userId, Integer orderStatus);

    // 订单完成
    void missionComplete(Long orderId);

    // 订单完成确认
    void completeOrder(Long orderId);

    // 取消订单
    void cancelOrder(Long orderId);
}
