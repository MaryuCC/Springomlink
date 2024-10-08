package com.cola.omlink.manager.controller;


import com.cola.omlink.manager.service.EmailService;
import com.cola.omlink.manager.service.UserInfoService;
import com.cola.omlink.repository.dto.h5.UserLoginDto;
import com.cola.omlink.repository.dto.h5.UserRegisterDto;
import com.cola.omlink.repository.vo.common.Result;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/user/")
public class LoginController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserInfoService userInfoService;


    // Register

    //1.1 send verify code
    @GetMapping("sendCode/{email}")
    public Result sendValidateCode(@PathVariable String email){
        emailService.sendValidateCode(email);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    // user register
    @PostMapping("register")
    public Result register(@PathVariable UserRegisterDto userRegisterDto){
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }


    // Login
    @PostMapping("login")
    public Result login(@RequestBody UserLoginDto userLoginDto){
        String token = userInfoService.login(userLoginDto);
        return Result.build(token,ResultCodeEnum.SUCCESS);
    }



    // Get user info based on token





}
