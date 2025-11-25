package com.example.umc9th_week5.domain.mission.entity;

import com.example.umc9th_week5.domain.member.entity.Member;
import com.example.umc9th_week5.domain.mission.code.MemberMissionErrorCode;
import com.example.umc9th_week5.domain.mission.enums.MissionStatus;
import com.example.umc9th_week5.global.apiPayload.exception.GeneralException;
import com.example.umc9th_week5.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name="member_mission")
public class MemberMission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id")
    private Mission mission;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MissionStatus status = MissionStatus.IN_PROGRESS;

    public void updateStatus(MissionStatus status) {
        if (this.status == MissionStatus.COMPLETED) {
            throw new GeneralException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }
        this.status = status;
    }

}
