package com.example.umc_9th_spring.domain.mission.service.query;


import com.example.umc_9th_spring.domain.mission.converter.MissionConverter;
import com.example.umc_9th_spring.domain.mission.dto.res.MissionResDTO;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import com.example.umc_9th_spring.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<MissionResDTO.MissionInfo> getActiveMissionsByLocation(Long locationId, int page, int size) {
        Page<Mission> missions = missionRepository.findActiveMissionsByLocation(locationId, LocalDateTime.now(), PageRequest.of(page, size));
        return missions.map(MissionConverter::toMissionInfoDTO);
        //원래 missions.map(mission -> MissionConverter.toMissionInfoDTO(mission));인데 메서드 참조 문법 :: 를 통해 간략화.
        //Page<T>는 .map()함수를 제공함.
    }
}
