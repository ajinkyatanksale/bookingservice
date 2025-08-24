package com.atcorp.eventmanagement.annotations;

import com.atcorp.eventmanagement.util.annotationProcessor.ValidateSeatMap;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateSeatMap.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface ValidSeatMap {
    String message() default "Seat map must contains array of section. Each section object must have number of rows and seats per row.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
