package com.example.umc_9th_spring.domain.user.service.query;

import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.converter.ReviewConverter;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.review.repository.ReviewRepository;
import com.example.umc_9th_spring.domain.user.code.UserErrorCode;
import com.example.umc_9th_spring.domain.user.converter.UserConverter;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import com.example.umc_9th_spring.domain.user.entity.User;
import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.user.enums.UserMissionMapStatus;
import com.example.umc_9th_spring.domain.user.exception.UserException;
import com.example.umc_9th_spring.domain.user.repository.UserMissionMapRepository;
import com.example.umc_9th_spring.domain.user.repository.UserRepository;
import com.example.umc_9th_spring.global.util.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.DESC;


@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMissionMapRepository userMissionMapRepository;
    private final ReviewRepository reviewRepository;

    //유저 정보 조회
    @Override
    public UserResDTO.UserInfo getUserInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toUserInfoDTO(user);
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

    @Override
    public ReviewResDTO.MyReviewPage getMyReviews(Long userId, int page){
        if(!userRepository.existsById(userId)){
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }
        Page<Review> result = reviewRepository.findMyReviews(userId,
                PageUtils.toPageableFromOneIndex(page, Sort.by(DESC, "createdAt"))
                );

        return ReviewConverter.toMyReviewPage(result);
    }

    @Override
    public MissionResDTO.UserMissionPage getInProgressMissions(Long userId, int page){

        if(!userRepository.existsById(userId)){
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        Page<UserMissionMap> missions = userMissionMapRepository.findByUserIdAndStatus(
                userId,
                UserMissionMapStatus.in_progress,
                PageUtils.toPageableFromOneIndex(page, Sort.by(DESC, "receivedAt"))
        );

        return MissionConverter.toUserMissionPage(missions);
    }


}
