package com.recarga.application.dto;

public class LoginResponse {

    private final String token;
    private final String expiresIn;

    public LoginResponse(String token, String expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public String getExpiresIn() {
        return expiresIn;
    }
}
