package com.cohort3.week2.springmvcRESTAPI.Week2L_4ServiceLayer.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation , String> {
    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {

        if (inputRole==null)return false;
        List<String> roles = List.of("ADMIN" ,"USER");
        return roles.contains(inputRole);
    }
}
