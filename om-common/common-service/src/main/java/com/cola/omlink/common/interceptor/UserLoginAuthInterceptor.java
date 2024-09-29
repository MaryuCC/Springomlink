package com.cola.omlink.common.interceptor;

import static com.cola.omlink.utils.ExtractHeaderUtil.extractToken;
import com.alibaba.fastjson.JSON;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.utils.AuthContextUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;


public class UserLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        String token = extractToken(request);
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        AuthContextUtil.setUserInfo(JSON.parseObject(userJson, User.class));

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
