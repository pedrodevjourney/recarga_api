package com.recarga.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    @Size(max = 255, message = "email must be at most 255 characters")
    private String email;

    @NotBlank(message = "password is required")
    @Size(max = 100, message = "password must be at most 100 characters")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
