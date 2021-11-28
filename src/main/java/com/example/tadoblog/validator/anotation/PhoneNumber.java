package com.example.tadoblog.validator.anotation;

import com.example.tadoblog.validator.PhoneValidator;
import com.example.tadoblog.validator.data.PhoneValidationType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface PhoneNumber {
    String message() default "{javax.validation.constraints.NotBlank.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    PhoneValidationType type() default PhoneValidationType.FULL;
}
