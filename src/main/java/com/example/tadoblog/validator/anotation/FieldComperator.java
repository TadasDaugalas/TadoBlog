package com.example.tadoblog.validator.anotation;

import com.example.tadoblog.validator.FieldCompereValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldCompereValidator.class)
public @interface FieldComperator {
    String message() default "{field.comperator.constraints.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String firstField();
    String secondField();

}
