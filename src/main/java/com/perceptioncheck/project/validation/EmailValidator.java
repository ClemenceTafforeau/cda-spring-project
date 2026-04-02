package com.perceptioncheck.project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        String RFC5322regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        if (email == null || email.isBlank()) {
            return false;
        }

        return Pattern.compile(RFC5322regexPattern)
                .matcher(email)
                .matches();
    }
}
