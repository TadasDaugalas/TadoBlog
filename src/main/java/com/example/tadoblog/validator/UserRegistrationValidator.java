package com.example.tadoblog.validator;
import com.example.tadoblog.data.UserRegistration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","userRegistrationValidator.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"surname","userRegistrationValidator.surname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"country","userRegistrationValidator.country");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"city","userRegistrationValidator.city");
    }
}
