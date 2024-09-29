package com.cola.omlink.manager.config.auth;

import static com.cola.omlink.manager.constant.RoleConstant.ROLE_PREFIX;

import com.alibaba.fastjson.JSON;
import com.cola.omlink.repository.entity.user.User;
import com.cola.omlink.repository.vo.common.RolesEnum;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class StoredApiToken {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public UsernamePasswordAuthenticationToken verifyToken(String token) {
        try {
            String userJson = redisTemplate.opsForValue().get("user:login" + token);
            User user = JSON.parseObject(userJson, User.class);
            String name = user.getUserName();
            Long userId = user.getId();
            //TODO("get user role by id, default USER")
            String userRole = ROLE_PREFIX + RolesEnum.USER.name();

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
