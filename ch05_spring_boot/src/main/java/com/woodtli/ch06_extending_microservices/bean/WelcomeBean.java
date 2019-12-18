package com.woodtli.ch06_extending_microservices.bean;

public class WelcomeBean {
    private String message;

    public WelcomeBean(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}