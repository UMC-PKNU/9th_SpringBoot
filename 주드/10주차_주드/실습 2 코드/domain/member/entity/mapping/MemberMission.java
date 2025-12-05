package com.example.umc9th.domain.member.entity.mapping;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.enums.MemberMissionStatus;
import com.example.umc9th.domain.mission.entity.Mission;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_mission")

public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberMissionStatus status = MemberMissionStatus.PROGRESS;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime due_date;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime created_at ;
}
