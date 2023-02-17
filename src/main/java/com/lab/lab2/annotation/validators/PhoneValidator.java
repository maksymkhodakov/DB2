package com.lab.lab2.annotation.validators;

import com.lab.lab2.annotation.ValidPhone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class PhoneValidator implements ConstraintValidator<ValidPhone, String> {
    private String message;
    @Override
    public void initialize(ValidPhone constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (nonNull(phone) && phone.contains("+38")) {
            return true;
        } else {
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
    }
}
