package com.example.tadoblog.validator;

import com.example.tadoblog.validator.anotation.FieldComperator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class FieldCompereValidator implements ConstraintValidator<FieldComperator,Object> {
    private String firstField;
    private String secondField;
    @Override
    public void initialize(FieldComperator constraintAnnotation) {
        firstField=constraintAnnotation.firstField();
        secondField=constraintAnnotation.secondField();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object firstValue=getFieldVAlue(o,firstField);
            Object secondValue=getFieldVAlue(o,secondField);

            return firstValue!=null && firstValue.equals(secondValue);
        } catch (Exception e) {
            //TODO log exeption
        }

        return false;
    }

    private Object getFieldVAlue (Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
       Class<?>objectClass=object.getClass();
       Field field=objectClass.getDeclaredField(fieldName);
       field.setAccessible(true);
       return field.get(object);
    }
}
