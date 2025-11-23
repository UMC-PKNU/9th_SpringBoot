package com.example.umc9th.global.validator;

import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.annotation.ExistMembers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberExistValidator implements ConstraintValidator<ExistMembers, List<Long>> {

    private final MemberRepository memberRepository;

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {

        boolean isValid = values.stream()
                .allMatch(value -> memberRepository.existsById(value));

                if(!isValid){
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(MemberErrorCode.MEMBER_NOT_FOUND.getMessage()).addConstraintViolation();
                }

                return isValid;
    }
}
