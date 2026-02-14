package com.recarga.application.dto;

import java.util.UUID;

public class UserResponse {

    private final UUID id;
    private final String fullName;
    private final String email;

    public UserResponse(UUID id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
