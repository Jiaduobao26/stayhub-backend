package com.lee.stayhub.model;

public record RegisterRequest(
        String username,
        String password,
        UserRole role
) {

}
