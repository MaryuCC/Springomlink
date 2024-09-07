package com.cola.omlink.manager.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.cola.omlink.common.exception.OmException;
import com.cola.omlink.manager.mapper.OrderMapper;
import com.cola.omlink.manager.service.OrderService;
import com.cola.omlink.repository.dto.order.OrderDto;
import com.cola.omlink.repository.entity.article.Project;
import com.cola.omlink.repository.entity.order.Order;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.cola.omlink.utils.AuthContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    //接受订单
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
        order.setCustomerId(user.getId());
        order.setCustomerName(user.getNickName());
        order.setStatus(1);
        order.setProjectId(project.getId());

        // 设置金额
        BigDecimal totalAmount = new BigDecimal(0);
        order.setOriginalPrice(project.getProjectPrice());

        order.setTotalPrice(project.getProjectPrice());

        order.setStatus(1);

        // 4 添加数据到order表中
        orderMapper.save();

        // 5 返回订单id

        return order.getId();
    }




}
