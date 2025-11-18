package com.example.umc_9th_spring.domain.user.entity;

import com.example.umc_9th_spring.domain.mission.code.MissionErrorCode;
import com.example.umc_9th_spring.domain.mission.exception.MissionException;
import com.example.umc_9th_spring.domain.user.enums.UserMissionMapStatus;
import com.example.umc_9th_spring.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "user_mission")
public class UserMissionMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private UserMissionMapStatus status;

    @Column(name = "received_at")
    private LocalDateTime receivedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    public void complete() {
        if (this.status == UserMissionMapStatus.completed) {
            throw new MissionException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        this.status = UserMissionMapStatus.completed;
        this.completedAt = LocalDateTime.now();
    }

}
