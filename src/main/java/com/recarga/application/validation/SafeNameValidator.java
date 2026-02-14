package com.recarga.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Valida nome sem regex (evita ReDoS).
 * Permite apenas letras (qualquer idioma), espaços, hífen e apóstrofo.
 */
public class SafeNameValidator implements ConstraintValidator<SafeName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // @NotBlank trata vazio
        }
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isLetter(c)) continue;
            if (Character.isSpaceChar(c)) continue;
            if (c == '-' || c == '\'') continue;
            return false;
        }
        return true;
    }
}
