package com.recarga.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Valida senha forte sem usar regex (evita ReDoS).
 * Exige pelo menos uma letra e um dígito.
 */
public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // @NotBlank trata vazio
        }
        boolean hasLetter = false;
        boolean hasDigit = false;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isLetter(c)) hasLetter = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (hasLetter && hasDigit) return true;
        }
        return hasLetter && hasDigit;
    }
}
