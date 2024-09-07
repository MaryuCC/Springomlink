package com.cola.omlink.manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {
        "com.cola.omlink.manager.mapper",
})
public class ServiceAutoConfig {
}
