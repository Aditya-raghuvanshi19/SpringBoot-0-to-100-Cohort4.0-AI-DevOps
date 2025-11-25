package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD , ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        EmployeeRoleValidator.class
})
public @interface EmployeeRoleValidation {
    String message() default "role of the employee can be USER or ADMIN only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
