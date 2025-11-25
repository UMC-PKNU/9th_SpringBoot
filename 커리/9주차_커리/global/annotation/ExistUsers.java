package com.example.umc_9th_spring.global.annotation;

import com.example.umc_9th_spring.global.vaildator.UserExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UserExistValidator.class}) //검증 로직을 담당할 클래스 지정
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER}) //커스텀 어노테이션을 메소드, 필드, 파라미터에 붙일 수 있음
@Retention(RetentionPolicy.RUNTIME) //런타임 시점에도 유지되어 Vaildator가 접근 가능하도록 함.
public @interface ExistUsers {
    // 검증 실패 시 기본 메시지
    String message() default "존재하지 않는 사용자입니다.";

    // 아래 두 개는 Bean Validation 기본 속성 (그대로 두면 됨)
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
