package com.example.umc9th.domain.mission.dto; 

import java.time.LocalDateTime;


public record MissionSummaryDto(
    String locatedAt,    // s.locatedAt (가게 위치)
    String storeType,    // s.storeType (가게 종류)
    String storeName,    // s.name (가게 이름)
    LocalDateTime dueDate, // mm.dueDate (미션 마감 기한)
    String content,      // m.content (미션 내용)
    Integer reward        // m.reward (미션 보상)
) {}