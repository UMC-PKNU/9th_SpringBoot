package com.example.umc_9th_spring.domain.user.converter;

import com.example.umc_9th_spring.domain.user.dto.req.UserReqDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;

public class UserConverter {

    // 요청 DTO -> Entity
    public static User toEntity(UserReqDTO.SignUp dto) {
        return User.builder()
                .name(dto.getUsername())
                .email(dto.getEmail())
                .passwordHash(dto.getPassword())
                .phone(dto.getPhone())
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
