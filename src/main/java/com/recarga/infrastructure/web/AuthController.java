package com.recarga.infrastructure.web;

import com.recarga.application.LoginUser;
import com.recarga.application.RegisterUser;
import com.recarga.application.dto.LoginRequest;
import com.recarga.application.dto.LoginResponse;
import com.recarga.application.dto.RegisterRequest;
import com.recarga.application.dto.UserResponse;
import com.recarga.application.exceptions.EmailAlreadyExistsException;
import com.recarga.application.exceptions.InvalidCredentialsException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RegisterUser registerUser;
    private final LoginUser loginUser;

    public AuthController(RegisterUser registerUser, LoginUser loginUser) {
        this.registerUser = registerUser;
        this.loginUser = loginUser;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = registerUser.execute(request);
        return ResponseEntity.created(URI.create("/api/users/" + response.getId())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginUser.execute(request);
        return ResponseEntity.ok(response);
    }
}
