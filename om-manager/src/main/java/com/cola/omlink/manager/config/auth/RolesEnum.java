package com.cola.omlink.manager.config.auth;

import lombok.Getter;

@Getter
public enum RolesEnum {
    USER(0),
    ADMIN(1);

    private final int code;

    RolesEnum(int code) {
        this.code = code;
    }
}
