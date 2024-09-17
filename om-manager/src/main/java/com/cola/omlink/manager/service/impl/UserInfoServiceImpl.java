package com.cola.omlink.manager.service.impl;


import com.alibaba.fastjson.JSON;
import com.cola.omlink.common.exception.OmException;
import com.cola.omlink.manager.mapper.UserMapper;
import com.cola.omlink.manager.service.UserInfoService;
import com.cola.omlink.repository.dto.h5.UserLoginDto;
import com.cola.omlink.repository.dto.h5.UserRegisterDto;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.repository.vo.common.ResultCodeEnum;
import com.cola.omlink.repository.vo.h5.UserVo;
import com.cola.omlink.utils.AuthContextUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.cola.omlink.manager.constant.RedisConstant.Email_Pre;
import static com.cola.omlink.manager.constant.RedisConstant.Login_Token_Pre;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    // 获取当前用户信息
    @Override
    public UserVo getCurrentUserInfo(String token) {
        // 从redis中根据token获取用户信息
//        String userJson = redisTemplate.opsForValue().get(Login_Token_Pre + token);
//        if(!StringUtils.hasText(userJson)){
//            throw new OmException(ResultCodeEnum.LOGIN_AUTH);
//        }
//        User user = JSON.parseObject(userJson, User.class);
        // 从Tread Local中获取用户信息
        User user = AuthContextUtil.getUserInfo();


        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);

        return userVo;
    }

    // user login
    @Override
    public String login(UserLoginDto userLoginDto) {
        // 1. Dto获取用户名和密码
        String userName = userLoginDto.getUserName();
        String password = userLoginDto.getPassword();

        // 2. 根据用户名查询数据库，得到用户信息
        User user = userMapper.selectByUsername(userName);

        // 3. 比较密码是否一致
        String database_password = user.getPassword();
        String md5_password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!database_password.equals(md5_password)){
            throw new OmException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 校验用户是否被禁用

        // 4. 生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        // 5. 把用户信息放到redis中
        redisTemplate.opsForValue().set(Login_Token_Pre+token,
                                            JSON.toJSONString(user),60, TimeUnit.MINUTES);

        // 返回token
        return token;
    }

    // user register
    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 1 userRegisterDto获取数据
        String userName = userRegisterDto.getUserName();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();


        // 2 验证码校验
        if(!isValidate(userName,code)){
            throw new OmException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // 3 校验用户名不能重复

        User user = userMapper.selectByUsername(userName);
        if(user != null){ //存在相同用户名
            throw new OmException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        // 4 封装添加数据，调用方法添加数据库
        user = new User();
        user.setUserName(userName);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setNickName(nickName);

        userMapper.save(user);

        // 5 从redis中删除发送的验证码
        redisTemplate.delete(userName);

    }


    //验证码校验
    private boolean isValidate(String userName, String code){
        // 2，1 从redis获取发送验证码
        String redisCode = redisTemplate.opsForValue().get(Email_Pre + userName);

        // 2.2 获取输入的验证码，进行比对
        if(redisCode.equals(code)){
            return false;
        }
        return true;
    }

}
