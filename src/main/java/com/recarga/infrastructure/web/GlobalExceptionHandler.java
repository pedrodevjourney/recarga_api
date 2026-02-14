package com.recarga.infrastructure.web;

import com.recarga.application.exceptions.EmailAlreadyExistsException;
import com.recarga.application.exceptions.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExists() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email already registered"));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(e -> e.getField(), e -> e.getDefaultMessage() != null ? e.getDefaultMessage() : "invalid", (a, b) -> b));
        return ResponseEntity.badRequest().body(Map.of("message", "Validation failed", "errors", errors));
    }
}
