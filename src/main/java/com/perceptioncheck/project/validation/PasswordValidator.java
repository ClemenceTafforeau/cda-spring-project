package com.perceptioncheck.project.validation;

import com.perceptioncheck.project.dto.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<ValidPassword, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if (obj instanceof RegisterDTO) {
            // At least 6 characters long, including a lowercase letter, an uppercase letter, a special character
            // amongst % * £ - + = and a digit
            String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[%*$+-=]).{6,}$";
            RegisterDTO registerDTO = (RegisterDTO) obj;

            return registerDTO.getPassword() != null &&
                    registerDTO.getPassword().equals(registerDTO.getConfirmPassword()) &&
                    Pattern.compile(regex).matcher(registerDTO.getPassword()).matches();
        } else {
            return false;
        }
    }
}
