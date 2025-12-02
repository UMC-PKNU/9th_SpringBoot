package com.example.umc_9th_spring.global.vaildator;

import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import com.example.umc_9th_spring.global.annotation.ExistUsers;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExistValidator implements ConstraintValidator<ExistUsers, Long> { //<검증할 어노테이션, 검증할 값의 타입>

    private final UserRepository userRepository;

    @Override
    public void initialize(ExistUsers constraintAnnotation) {}


    //검증 대상(@ExistUsers가 붙은)이 메서드 인자로 주입됨.
    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {

        // 1null 체크
        if (userId == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("userId가 비어 있습니다.")
                    .addConstraintViolation();
            return false;
        }

        // 존재 여부 확인
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            // 기본 메시지 비활성화하고 커스텀 메시지 덮어쓰기
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    UserErrorCode.USER_NOT_FOUND.getMessage()   // ex) "존재하지 않는 사용자입니다."
            ).addConstraintViolation();
        }

        // true면 통과, false면 위에서 메시지 설정된 채로 반환
        return exists;
    }

}

