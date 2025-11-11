package com.example.umc_9th_spring.domain.user.service.query;

import com.example.umc_9th_spring.domain.inquiry.dto.res.InquiryResDTO;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.review.dto.res.ReviewResDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserQueryService {
    //유저 정보 조회하는 메서드 만들 예정.
    UserResDTO.UserInfo getUserInfo(Long userId);

    List<ReviewResDTO.ReviewSummary> getUserReviews(Long userId);

    List<InquiryResDTO.InquirySummary> getUserInquiries(Long userId);

    Page<MissionResDTO.MissionInfo> getUserMissionsByStatus(Long userId, String status, int page, int size);
}
