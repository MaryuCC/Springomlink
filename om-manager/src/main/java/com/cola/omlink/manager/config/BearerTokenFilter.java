package com.cola.omlink.manager.config;

import com.cola.omlink.manager.config.auth.StoredApiToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BearerTokenFilter extends OncePerRequestFilter {
    private final StoredApiToken storedApiToken;
    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer\\s+(\\S+)$");

    public BearerTokenFilter(StoredApiToken storedApiToken) {
        this.storedApiToken = storedApiToken;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String tokenText = extractToken(request);

        if (tokenText != null) {
            Authentication authentication = storedApiToken.verifyToken(tokenText);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        Matcher matcher = BEARER_PATTERN.matcher(authHeader != null ? authHeader : "");
        if (matcher.find()) {
            return matcher.group(1);
        }
        return request.getHeader("x-vapi-secret");
    }

    private void storeTokenInContext(Authentication token) {
        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        var context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(token);
        SecurityContextHolder.setContext(context);
    }

    public static boolean isBearerAuthRequest(HttpServletRequest request) {
        return request.getHeader("Authorization") != null && BEARER_PATTERN.matcher(request.getHeader("Authorization")).find();
    }
}
