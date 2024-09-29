package com.cola.omlink.manager.controller;

import static com.cola.omlink.utils.ExtractHeaderUtil.extractToken;

import com.cola.omlink.manager.service.UserInfoService;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.cola.omlink.repository.vo.h5.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("api/user/userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    // get current user info
    @GetMapping("auth/getCurrentUserInfo")
    public Result getCurrentUserInfo(HttpServletRequest request){
        String token = extractToken(request);
        UserVo userVo = userInfoService.getCurrentUserInfo(token);
        return Result.build(userVo,ResultCodeEnum.SUCCESS);
    }


}
