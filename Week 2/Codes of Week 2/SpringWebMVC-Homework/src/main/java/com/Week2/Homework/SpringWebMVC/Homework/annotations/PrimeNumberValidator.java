package com.Week2.Homework.SpringWebMVC.Homework.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Integer> {
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        for(int i=2;i<=Math.sqrt(integer);i++){
            if(integer%i==0)return false;
        }
        return true;
    }
}
