package com.example.umc_9th_.domain.user.converter;

import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.dto.UserRequest;

import java.time.LocalDateTime;

public class UserConverter {

    // Request DTO -> User Entity 변환 (저장용)
    public static User toUser(UserRequest.JoinDTO request) {
        String hashedPassword = "PLACEHOLDER_FOR_HASHED_PASSWORD";

        return User.builder()
                .name(request.getName())
                .password(hashedPassword)
                .nickname(request.getNickname())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .email(request.getEmail())

                // @Builder.Default로 설정된 userPoint, userState 등은 생략 가능
                .build();
    }

    // User 엔티티 -> MyPageInfoDTO
    public static UserResponse.MyPageInfoDTO toMyPageInfoDTO(User user) {
        return UserResponse.MyPageInfoDTO.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .point(user.getUserPoint())
                .build();
    }

    // User 엔티티 -> JoinResultDTO
    public static UserResponse.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponse.JoinResultDTO.builder()
                .userId(user.getId())
                .createdAt(LocalDateTime.now()) // 실제로는 user.getCreatedAt() 사용
                .build();
    }
}