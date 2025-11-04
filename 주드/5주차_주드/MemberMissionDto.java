package com.example.umc9th.domain.mission.dto; 

public record MemberMissionDto(
    String storeName, // 상점 이름 (s.name)
    String content,   // 미션 내용 (m.content)
    Integer reward,   // 미션 보상 (m.reward)
    Boolean isComplete // 미션 완료 여부 (mm.isComplete)
) {}