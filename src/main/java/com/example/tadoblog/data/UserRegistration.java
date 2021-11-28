package com.example.tadoblog.data;

import com.example.tadoblog.validator.anotation.FieldComperator;
import com.example.tadoblog.validator.anotation.PhoneNumber;
import com.example.tadoblog.validator.data.PhoneValidationType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@FieldComperator(firstField = "password",secondField = "repeatPassword")
public class UserRegistration {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String username;
    private String country;
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipCode;
    @NotBlank
    @PhoneNumber(type = PhoneValidationType.PART)
    private String phoneNumber;
    @NotBlank
    @Min(8)
    private String password;
    @NotBlank
    @Min(8)
    private String repeatPassword;
}

