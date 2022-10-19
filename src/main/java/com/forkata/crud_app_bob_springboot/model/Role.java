package com.forkata.crud_app_bob_springboot.model;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
