package com.example.umc_9th_.domain.user.entity;

import com.example.umc_9th_.domain.base.entity.BaseEntity;
import com.example.umc_9th_.domain.mission.entity.Mission;
import com.example.umc_9th_.domain.user.enums.MissionStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "user_mission")
public class UserMission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private MissionStatus status = MissionStatus.NOT_STARTED;

    private LocalDateTime startedAt; // 시작하기전에는 NULL가능

    private LocalDateTime completedAt; // 끝내기전에는 NULL가능

    @Column(nullable = false, length = 20)
    private String authCode;

    // 미션 조회용 생성자
    public UserMission(Long id,
                       Mission mission,
                       MissionStatus status,
                       LocalDateTime startedAt,
                       LocalDateTime completedAt) {
        this.id = id;
        this.mission = mission;
        this.status = status;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }
}
