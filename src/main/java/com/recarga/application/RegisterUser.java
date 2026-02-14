package com.recarga.application;

import com.recarga.application.dto.RegisterRequest;
import com.recarga.application.dto.UserResponse;
import com.recarga.application.exceptions.EmailAlreadyExistsException;
import com.recarga.domain.User;
import com.recarga.domain.ports.PasswordHasher;
import com.recarga.domain.ports.UserRepository;

import java.util.UUID;

public class RegisterUser {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public RegisterUser(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    public UserResponse execute(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        String passwordHash = passwordHasher.hash(request.getPassword());
        User user = new User(
                UUID.randomUUID(),
                request.getFullName().trim(),
                request.getEmail().trim().toLowerCase(),
                passwordHash
        );
        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(), saved.getFullName(), saved.getEmail());
    }
}
