package com.cola.omlink.manager.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "omlink.auth")
public class AuthProperties {
    private List<String> noAuthUrls;

    public List<String> getNoAuthUrls() {
        return noAuthUrls;
    }

    public void setNoAuthUrls(List<String> noAuthUrls) {
        this.noAuthUrls = noAuthUrls;
    }
}
