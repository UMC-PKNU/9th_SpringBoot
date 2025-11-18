package com.example.umc_9th_spring.domain.user.service.query;

import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.user.dto.res.UserResDTO;
import org.springframework.data.domain.Page;


public interface UserQueryService {
    //유저 정보 조회하는 메서드 만들 예정.
    UserResDTO.UserInfo getUserInfo(Long userId);

    Page<MissionResDTO.MissionInfo> getUserMissionsByStatus(Long userId, String status, int page, int size);
}
