package com.example.umc_9th_spring.domain.user.converter;

import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.enums.UserStatus;
import com.example.umc_9th_spring.global.auth.enums.Role;

public class UserConverter {

    // 요청 DTO -> Entity
    public static User toEntity(UserReqDTO.SignUp dto, String password ,Role role) {
        return User.builder()
                .email(dto.getEmail())
                .passwordHash(password)
                .role(role)
                .name(dto.getUsername())
                .phone(dto.getPhone())
                .status(UserStatus.ACTIVE)
                .point(0)
                .notificationEnabled(true)
                .build();
    }

    // Entity -> 응답 DTO (회원가입 결과 or 회원정보 조회)
    public static UserResDTO.UserInfo toUserInfoDTO(User user) {
        return UserResDTO.UserInfo.builder()
                .id(user.getId())
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }

    // 로그인 응답용 DTO
    public static UserResDTO.LoginResult toLoginDTO(User user, String token) {
        return UserResDTO.LoginResult.builder()
                .token(token)
                .username(user.getName())
                .build();
    }
}
