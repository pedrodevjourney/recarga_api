package com.recarga.application.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = SafeNameValidator.class)
public @interface SafeName {

    String message() default "Nome pode conter apenas letras, espaços, hífen e apóstrofo";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
