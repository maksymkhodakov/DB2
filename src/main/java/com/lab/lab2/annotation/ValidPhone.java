package com.lab.lab2.annotation;

import com.lab.lab2.annotation.validators.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneValidator.class})
public @interface ValidPhone {
    String message() default "INVALID PHONE";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
