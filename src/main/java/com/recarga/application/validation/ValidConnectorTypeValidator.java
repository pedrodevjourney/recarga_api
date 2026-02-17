package com.recarga.application.validation;

import com.recarga.domain.ConnectorType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ValidConnectorTypeValidator implements ConstraintValidator<ValidConnectorType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // @NotBlank trata vazio
        }
        return Arrays.stream(ConnectorType.values())
                .anyMatch(e -> e.name().equals(value));
    }
}
