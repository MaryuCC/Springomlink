package com.cola.omlink.manager.config.auth;

import com.alibaba.fastjson.JSON;
import com.cola.omlink.manager.service.UserInfoService;
import com.cola.omlink.repository.entity.user.User;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import static com.cola.omlink.manager.constant.RedisConstant.Login_Token_Pre;

@Service
public class StoredApiToken {
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    UserInfoService userInfoService;

    public UsernamePasswordAuthenticationToken verifyToken(String token) {
        try {
            String userJson = redisTemplate.opsForValue().get(Login_Token_Pre + token);
            User user = JSON.parseObject(userJson, User.class);
            String name = user.getUserName();

            String userRole = userInfoService.getUserRoleByUserId(user.getId()).name();

            return new UsernamePasswordAuthenticationToken(
                name,
                null,
                Collections.singletonList(new SimpleGrantedAuthority(userRole))
            );
        } catch (Exception e) {
            return null;
        }
    }
}
