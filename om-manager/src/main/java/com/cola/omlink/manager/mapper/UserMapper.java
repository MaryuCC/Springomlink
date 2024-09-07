package com.cola.omlink.manager.mapper;


import com.cola.omlink.repository.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 校验用户名不能重复
    User selectByUsername(String username);


    // 注册
    void save(User user);
}
