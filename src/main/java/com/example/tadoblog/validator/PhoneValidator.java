package com.example.tadoblog.validator;

import com.example.tadoblog.validator.anotation.PhoneNumber;
import com.example.tadoblog.validator.data.PhoneValidationType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneNumber, String> {
    private PhoneValidationType type;

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
        type =constraintAnnotation.type();
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext) {
        if(number == null) {
            return false;
        }
        return PhoneValidationType.FULL.equals(type) ? isFullPhoneNumberValid(number) : isPartPhoneNumberValid(number);
    }

    private boolean isPartPhoneNumberValid(String number) {
        try {
            Long.parseLong(number);
        }catch (NumberFormatException e) {
            return false;
        }
        return number.startsWith("8") && number.length() == 9;
    }

    private boolean isFullPhoneNumberValid(String number) {
        try {
            String val = number.substring(1, number.length() - 1);
            long numb = Long.parseLong(val);
        }catch (NumberFormatException e) {
            return false;
        }

        return number.startsWith("+370") && number.length() == 12;
    }
}
