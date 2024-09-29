package com.cola.omlink.manager.mapper;

import com.cola.omlink.repository.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    UserInfo selectByUserId(Long userId);
}
