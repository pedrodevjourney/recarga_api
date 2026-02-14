package com.recarga.infrastructure.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recarga.application.dto.LoginRequest;
import com.recarga.application.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void register_returns201_andUserData() throws Exception {
        String email = "user-" + System.currentTimeMillis() + "@example.com";
        RegisterRequest request = new RegisterRequest("Full Name", email, "password123");

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.fullName").value("Full Name"))
                .andExpect(jsonPath("$.email").value(email))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.passwordHash").doesNotExist());
    }

    @Test
    void register_duplicateEmail_returns409() throws Exception {
        String email = "dup-" + System.currentTimeMillis() + "@example.com";
        RegisterRequest request = new RegisterRequest("User One", email, "password123");

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already registered"));
    }

    @Test
    void login_returns200_andToken() throws Exception {
        String email = "login-" + System.currentTimeMillis() + "@example.com";
        RegisterRequest registerRequest = new RegisterRequest("Login User", email, "mypassword123");
        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isCreated());

        LoginRequest loginRequest = new LoginRequest(email, "mypassword123");
        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.expiresIn").value("24h"));
    }

    @Test
    void login_invalidCredentials_returns401() throws Exception {
        LoginRequest loginRequest = new LoginRequest("nonexistent@example.com", "wrongpassword");
        mvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));
    }

    @Test
    void register_validationFailure_returns400() throws Exception {
        RegisterRequest request = new RegisterRequest("", "invalid-email", "short");
        mvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }
}
