package com.recarga.infrastructure.config;

import com.recarga.application.LoginUser;
import com.recarga.application.RegisterUser;
import com.recarga.domain.ports.PasswordHasher;
import com.recarga.domain.ports.TokenProvider;
import com.recarga.domain.ports.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfig {

    @Bean
    public RegisterUser registerUser(UserRepository userRepository, PasswordHasher passwordHasher) {
        return new RegisterUser(userRepository, passwordHasher);
    }

    @Bean
    public LoginUser loginUser(UserRepository userRepository, PasswordHasher passwordHasher, TokenProvider tokenProvider) {
        return new LoginUser(userRepository, passwordHasher, tokenProvider);
    }
}
