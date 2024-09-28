package com.cola.omlink.manager.config;

import com.cola.omlink.manager.config.auth.StoredApiToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final BearerTokenFilter bearerTokenFilter;

    @Autowired
    public SecurityConfig(BearerTokenFilter bearerTokenFilter) {
        this.bearerTokenFilter = bearerTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Autowired StoredApiToken storedApiToken) throws Exception {
        http.addFilterBefore(new BearerTokenFilter(storedApiToken), BasicAuthenticationFilter.class)
                .authorizeRequests()
                // Allow public access to specific endpoints
                .requestMatchers("/api/order/**").authenticated()
                .requestMatchers("/api/dashboard/**").authenticated()
                .requestMatchers("/api/project/**").authenticated()
                .requestMatchers("/api/**").permitAll()
                // Require authentication for other requests
                .anyRequest().authenticated();

        return http.build();
    }
}
