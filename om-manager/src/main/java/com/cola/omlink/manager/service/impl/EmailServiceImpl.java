package com.cola.omlink.manager.service.impl;

import com.cola.omlink.manager.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

import static com.cola.omlink.manager.constant.RedisConstant.Email_Pre;
import static com.cola.omlink.manager.util.sendAuthCodeEmail.sendAuthCodeEmail;


@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendValidateCode(String email) {
        // Search if there is validate code cache in redis
        String code = redisTemplate.opsForValue().get(Email_Pre + email);
        if(StringUtils.hasText(code)){
            return;
        }

        // if not, generate validate code
        String validateCode = RandomStringUtils.randomNumeric(6);
        log.info(validateCode);

        // put validate code into redis with 5 mins time limit
        redisTemplate.opsForValue().set(Email_Pre + email, validateCode, 5, TimeUnit.MINUTES);

        // send validate code
        sendAuthCodeEmail(email, validateCode);
    }
}
