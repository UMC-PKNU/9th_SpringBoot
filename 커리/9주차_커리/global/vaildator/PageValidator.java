package com.example.umc_9th_spring.global.vaildator;

import com.example.umc_9th_spring.global.annotation.ValidPage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {


    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {

        if(value <=0) {
            return false;
        }

        return true;
    }
}
