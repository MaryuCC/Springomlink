package com.cola.omlink.manager;


import com.cola.omlink.common.annotation.EnableUserLoginAuthInterceptor;
import io.minio.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cola.omlink"})
@EnableUserLoginAuthInterceptor
public class ManagerApplication {

    public static void main(String args[]){
        SpringApplication.run(ManagerApplication.class,args);
    }
}
