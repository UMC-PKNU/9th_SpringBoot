package com.example.umc.domain.member.dto;

import com.example.umc.domain.member.entity.Member;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.store.entity.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberMissionDto {
    private Double status;
    private Long moneyLowerLimit;
    private Long point;
    private String storeName;

    public static MemberMissionDto from(Object[] result) {
        return MemberMissionDto.builder()
                .status((Double) result[0])
                .moneyLowerLimit((Long) result[1])
                .point((Long) result[2])
                .storeName((String) result[3])
                .build();
    }
}
