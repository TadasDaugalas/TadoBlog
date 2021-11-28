package com.example.tadoblog.validator;
import com.example.tadoblog.data.UserRegistration;
import com.example.tadoblog.validator.anotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, UserRegistration> {
    @Override
    public boolean isValid(UserRegistration userRegistration, ConstraintValidatorContext constraintValidatorContext) {
        String password = userRegistration.getPassword();
        String repeatPassword = userRegistration.getRepeatPassword();

        return password != null && password.equals(repeatPassword);
    }
}