package com.cola.omlink.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import static com.cola.omlink.manager.constant.RedisConstant.Login_Token_Pre;

public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("token");
        String userJson = redisTemplate.opsForValue().get(Login_Token_Pre + token);
        AuthContextUtil.setUserInfo(JSON.parseObject(userJson, User.class));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
