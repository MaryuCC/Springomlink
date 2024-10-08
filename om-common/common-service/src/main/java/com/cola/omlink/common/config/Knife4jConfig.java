package com.cola.omlink.common.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    @Bean
    public GroupedOpenApi userApi() {      // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("dashboard-api")         // 分组名称
                .pathsToMatch("/api/**")  // 接口请求路径规则
                .build();
    }



    /***
     * @description 自定义接口信息
     */
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("omlink API document")
                        .version("1.0")
                        .description("omlink API document")
                        .contact(new Contact().name("Cola"))); // 设定作者
    }

}