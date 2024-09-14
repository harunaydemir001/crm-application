package com.jekirdek.user_service.annotation;


import com.jekirdek.common.constant.GeneralErrorCodeConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String minLengthMessage() default GeneralErrorCodeConstants.PASSWORD_MIN_LENGTH;

    String uppercaseMessage() default GeneralErrorCodeConstants.PASSWORD_UPPER_CASE;

    String lowercaseMessage() default GeneralErrorCodeConstants.PASSWORD_LOWER_CASE;

    String digitMessage() default GeneralErrorCodeConstants.PASSWORD_DIGIT;

    String specialCharMessage() default GeneralErrorCodeConstants.PASSWORD_SPECIAL_CHAR;

    String whitespaceMessage() default GeneralErrorCodeConstants.PASSWORD_WHITE_SPACE;
}