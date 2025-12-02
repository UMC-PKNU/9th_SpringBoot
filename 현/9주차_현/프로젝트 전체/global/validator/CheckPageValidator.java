package com.example.umc_9th_.global.validator;

import com.example.umc_9th_.domain.store.exception.code.StoreErrorCode;
import com.example.umc_9th_.global.annotation.CheckPage;
import com.example.umc_9th_.global.apiPayload.code.GeneralErrorCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < 1) { // 1보다 작으면 에러!
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(GeneralErrorCode.BAD_REQUEST.getMessage()).addConstraintViolation();
            return false;
        }
        return true;
    }
}