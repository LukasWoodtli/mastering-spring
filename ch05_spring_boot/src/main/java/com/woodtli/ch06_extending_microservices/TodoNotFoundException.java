package com.woodtli.ch06_extending_microservices;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String msg) {
        super(msg);
    }
}
