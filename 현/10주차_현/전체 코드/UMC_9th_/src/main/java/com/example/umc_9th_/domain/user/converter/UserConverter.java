package com.example.umc_9th_.domain.user.converter;

import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserMission;
import com.example.umc_9th_.domain.user.entity.UserPreference;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    // record는 getter가 getXXX()가 아니라 그냥 필드명()
    public static User toUser(UserRequest.JoinDTO request) {
        String hashedPassword = "PLACEHOLDER_FOR_HASHED_PASSWORD";

        return User.builder()
                .name(request.name())          // request.getName() -> request.name()
                .password(hashedPassword)
                .nickname(request.nickname())
                .gender(request.gender())
                .birthDate(request.birthDate())
                .address(request.address())
                .email(request.email())
                .build();
    }

    // 빌더 대신 생성자 사용
    public static UserResponse.JoinResultDTO toJoinResultDTO(User user) {
        return new UserResponse.JoinResultDTO(
                user.getId(),
                LocalDateTime.now()
        );
    }

    // User 엔티티 -> MyPageInfoDTO (Record)
    public static UserResponse.MyPageInfoDTO toMyPageInfoDTO(User user) {
        return new UserResponse.MyPageInfoDTO(
                user.getId(),
                user.getNickname(),
                user.getEmail(),
                user.getUserPoint()
        );
    }

    // UserPreference 리스트 만들기
    public static List<UserPreference> toUserPreferenceList(User user, List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        UserPreference.builder()
                                .user(user)
                                .category(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }

    public static UserResponse.MyMissionPageDTO toMyMissionPageDTO(Page<UserMission> missionPage) {
        List<UserResponse.MyMissionDTO> missionList = missionPage.stream()
                .map(UserConverter::toMyMissionDTO)
                .collect(Collectors.toList());

        return new UserResponse.MyMissionPageDTO(
                missionList,
                missionPage.getTotalElements(),
                missionPage.getTotalPages(),
                missionPage.isFirst(),
                missionPage.isLast(),
                missionPage.getNumber() + 1 // 페이지는 0부터 시작하니까 +1
        );
    }

    public static UserResponse.MyMissionDTO toMyMissionDTO(UserMission userMission) {
        return new UserResponse.MyMissionDTO(
                userMission.getId(),
                userMission.getMission().getStore().getStoreName(), // 가게 이름
                userMission.getMission().getRewardPoint(),          // 보상
                userMission.getMission().getMissionCondition(),     // 미션 내용
                userMission.getStatus(),                       // 상태
                userMission.getCompletedAt()                   // 완료일 (null일 수 있음)
        );
    }
}