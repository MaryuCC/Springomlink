package com.cola.omlink.manager.service;

import com.cola.omlink.repository.dto.h5.UserLoginDto;
import com.cola.omlink.repository.dto.h5.UserRegisterDto;
import com.cola.omlink.repository.vo.h5.UserVo;

public interface UserInfoService {

    // user register
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto);

    UserVo getCurrentUserInfo(String token);
}
