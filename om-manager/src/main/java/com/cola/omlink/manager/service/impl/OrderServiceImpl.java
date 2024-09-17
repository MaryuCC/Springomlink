package com.cola.omlink.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cola.omlink.common.exception.OmException;
import com.cola.omlink.manager.mapper.OrderMapper;
import com.cola.omlink.manager.mapper.ProjectMapper;
import com.cola.omlink.manager.service.OrderService;
import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.dto.product.ProjectDto;
import com.cola.omlink.repository.entity.product.Project;
import com.cola.omlink.repository.entity.order.Order;
import com.cola.omlink.repository.entity.product.ProjectDetail;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.cola.omlink.utils.AuthContextUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProjectMapper projectMapper;


    // 生成订单
    @Override
    public Long submitOrder(OrderDto orderDto) {
        // 1 获取订单项目
        Project project = orderDto.getProject();

        // 2 判断是否为空，抛出异常
        if(ObjectUtil.isEmpty(project)){
            throw new OmException(ResultCodeEnum.DATA_ERROR);
        }

        // 3 构建订单数据
        User user = AuthContextUtil.getUserInfo();
        Order order = new Order();
        order.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //order.setCustomerId(user.getId());
        order.setCustomerId(5l);
        //order.setCustomerName(user.getNickName());
        order.setCustomerName("zhangsan");
        order.setStatus(0);
        order.setProjectId(project.getId());
/*
        // 判断是否是自己发布的项目
        if(user.getId().equals(project.getHostId())){
            throw new OmException(ResultCodeEnum.ORDER_ERROR);
        }*/


        // 设置金额
        BigDecimal totalAmount = new BigDecimal(0);
        order.setOriginalPrice(project.getProjectPrice());

        order.setTotalPrice(project.getProjectPrice());

        // 4 添加数据到order表中
        orderMapper.save(order);

        // 5 返回订单id
        return order.getId();
    }


    // 获取用户订单分页列表
    @Override
    public PageInfo<Order> findOrderByPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page,limit);
        // 查询订单信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<Order> orderList = orderMapper.findUserPage(userId,orderStatus);

        return new PageInfo<>(orderList);
    }

    // 订单完成
    @Override
    public void missionComplete(Long orderId) {
        orderMapper.missionComplete(orderId);
    }

    // 订单完成确认
    @Override
    public void completeOrder(Long orderId) {
        orderMapper.completeOrder(orderId);

        // TODO 给接收人佣金报酬
    }

    // 取消订单
    @Override
    public void cancelOrder(Long orderId) {
        orderMapper.cancelOrder(orderId);
    }
}
