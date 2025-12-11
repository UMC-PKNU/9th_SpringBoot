package com.example.umc.domain.store.converter;

import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.store.dto.res.StoreResDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    // 1. 단일 객체 변환 (Entity -> DTO)
    // 리스트 변환 시 내부에서 사용하기 위해 만듭니다.
    public static StoreResDto.StoreMissionPreViewDto toMissionDto(Mission mission) {
        return StoreResDto.StoreMissionPreViewDto.builder()
                .missionId(mission.getId())
                .moneyLowerLimit(mission.getMoneyLowerLimit())
                .point(mission.getPoint())
                .build();
    }

    // 2. 페이징된 리스트 변환 (Page<Mission> -> DTO)
    public static StoreResDto.StoreMissionsDto toStoreMissionsDto(Page<Mission> result) {

        // (1) Page 안에 있는 Mission 리스트를 DTO 리스트로 변환
        List<StoreResDto.StoreMissionPreViewDto> missionList = result.stream()
                .map(StoreConverter::toMissionDto) // 위의 메서드를 사용
                .collect(Collectors.toList());

        // (2) 전체 응답 DTO 만들기 (페이징 정보 포함)
        return StoreResDto.StoreMissionsDto.builder()
                .missions(missionList)      // 변환된 리스트
                .size(missionList.size())  // 현재 페이지의 데이터 개수
                .totalPage(result.getTotalPages()) // 전체 페이지 수
                .totalElements(result.getTotalElements()) // 전체 데이터 개수
                .isFirst(result.isFirst())     // 첫 페이지인지?
                .isLast(result.isLast())       // 마지막 페이지인지?
                .build();
    }
}
