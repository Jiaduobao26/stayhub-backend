package com.lee.stayhub.authentication;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
        super("Username already exists");
    }
}
