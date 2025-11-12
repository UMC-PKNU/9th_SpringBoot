package com.example.umc_9th_spring.domain.user.service.query;

import com.example.umc_9th_spring.domain.inquiry.converter.InquiryConverter;
import com.example.umc_9th_spring.domain.inquiry.dto.res.InquiryResDTO;
import com.example.umc_9th_spring.domain.inquiry.entity.Inquiry;
import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.converter.ReviewConverter;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.converter.UserConverter;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.user.enums.UserMissionMapStatus;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserMissionMapRepository;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMissionMapRepository userMissionMapRepository;

    //유저 정보 조회
    @Override
    public UserResDTO.UserInfo getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toUserInfoDTO(user);
    }

    @Override
    public List<ReviewResDTO.ReviewSummary> getUserReviews(Long userId) {
        List<Review> reviews = userRepository.findUserReviews(userId);
        return reviews.stream()
                .map(ReviewConverter::toReviewSummaryDTO)
                .toList();
    }

    @Override
    public List<InquiryResDTO.InquirySummary> getUserInquiries(Long userId) {
        List<Inquiry> inquiries = userRepository.findAllByIdOrderByCreatedAtDesc(userId);
        return inquiries.stream()
                .map(InquiryConverter::toInquirySummaryDTO)
                .toList();
    }

    //미션 상태별, 유저별, 페이지 조건으로 조회
    @Override
    public Page<MissionResDTO.MissionInfo> getUserMissionsByStatus(Long userId, String status, int page, int size) {
        UserMissionMapStatus missionStatus;
        try {
            missionStatus = UserMissionMapStatus.valueOf(status.toUpperCase());
            //enum에 선언되지 않은 문자열 들어오면 Illegal 예외가 터진다고 함.
        } catch (IllegalArgumentException e) {
            throw new UserException(UserErrorCode.USER_INVALID_MISSION_STATUS);
        }
        Page<UserMissionMap> missions = userMissionMapRepository.findUserMissionsByStatus(
                userId, missionStatus, PageRequest.of(page, size)
        );
        return missions.map(MissionConverter::toMissionInfoDTO);
    }
}
