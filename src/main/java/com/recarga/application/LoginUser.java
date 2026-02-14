package com.recarga.application;

import com.recarga.application.dto.LoginRequest;
import com.recarga.application.dto.LoginResponse;
import com.recarga.application.exceptions.InvalidCredentialsException;
import com.recarga.domain.ports.PasswordHasher;
import com.recarga.domain.ports.TokenProvider;
import com.recarga.domain.ports.UserRepository;

import java.util.Optional;

public class LoginUser {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final TokenProvider tokenProvider;

    public LoginUser(UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.tokenProvider = tokenProvider;
    }

    public LoginResponse execute(LoginRequest request) {
        Optional<com.recarga.domain.User> userOpt = userRepository.findByEmail(request.getEmail().trim().toLowerCase());
        if (userOpt.isEmpty() || !passwordHasher.matches(request.getPassword(), userOpt.get().getPasswordHash())) {
            throw new InvalidCredentialsException();
        }
        com.recarga.domain.User user = userOpt.get();
        String token = tokenProvider.generate(user.getId());
        String expiresIn = "24h";
        return new LoginResponse(token, expiresIn);
    }
}
