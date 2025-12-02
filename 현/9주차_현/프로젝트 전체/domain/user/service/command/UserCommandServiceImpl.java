package com.example.umc_9th_.domain.user.service.command;

import com.example.umc_9th_.domain.store.entity.FoodCategory;
import com.example.umc_9th_.domain.store.repository.FoodCategoryRepository;
import com.example.umc_9th_.domain.user.converter.UserConverter;
import com.example.umc_9th_.domain.user.dto.UserRequest;
import com.example.umc_9th_.domain.user.dto.UserResponse;
import com.example.umc_9th_.domain.user.entity.User;
import com.example.umc_9th_.domain.user.entity.UserPreference;
import com.example.umc_9th_.domain.user.exception.UserException;
import com.example.umc_9th_.domain.user.exception.code.UserErrorCode;
import com.example.umc_9th_.domain.user.repository.UserPreferenceRepository;
import com.example.umc_9th_.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final UserPreferenceRepository userPreferenceRepository;

    @Override
    public UserResponse.JoinResultDTO joinUser(UserRequest.JoinDTO request) {

        // 1. User 엔티티 변환 및 저장
        User newUser = UserConverter.toUser(request);
        userRepository.save(newUser);

        // 2. 선호 카테고리(FoodCategory) 리스트 조회
        // record는 getPreferredCategoryIds()가 아니라 preferredCategoryIds()
        List<FoodCategory> foodCategoryList = request.preferredCategoryIds().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category)
                            .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));
                }).collect(Collectors.toList());

        // 3. UserPreference(중간 테이블) 생성 및 저장
        List<UserPreference> userPreferenceList = UserConverter.toUserPreferenceList(newUser, foodCategoryList);
        userPreferenceRepository.saveAll(userPreferenceList);

        return UserConverter.toJoinResultDTO(newUser);
    }

    @Override
    public UserResponse.LocationUpdateDTO updateLocation(Long userId, UserRequest.UpdateLocationDTO request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.NOT_FOUND));

        // Dirty Checking을 위한 임시 Setter 사용 (Entity에 Setter나 update 메서드 필요)
        // record는 getAddress()가 아니라 address()
        // user.setAddress(request.address());

        // Response DTO가 record이므로 builder() 대신 new 생성자를 사용
        return new UserResponse.LocationUpdateDTO(
                userId,
                request.address(), // getAddress() -> address()
                java.time.LocalDateTime.now()
        );
    }
}