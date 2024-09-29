package com.cola.omlink.manager.config;

import static com.cola.omlink.utils.ExtractHeaderUtil.extractToken;
import com.cola.omlink.manager.config.auth.StoredApiToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class BearerTokenFilter extends OncePerRequestFilter {
    private final StoredApiToken storedApiToken;

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
}
