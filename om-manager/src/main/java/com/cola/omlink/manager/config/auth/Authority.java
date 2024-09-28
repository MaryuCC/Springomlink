package com.cola.omlink.manager.config.auth;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

    private final String authorityAsString;
    private static final String ROLE_PREFIX = "ROLE_";

    public Authority(RolesEnum rolesEnum) {
        this.authorityAsString = ROLE_PREFIX + rolesEnum.name();
    }

    @Override
    public String getAuthority() {
        return authorityAsString;
    }
}
