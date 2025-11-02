package com.example.umc_9th_.domain.mission.entity;

import com.example.umc_9th_.domain.base.entity.BaseEntity;
import com.example.umc_9th_.domain.store.entity.Store;
import com.example.umc_9th_.domain.user.entity.UserMission;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false, length = 100)
    private String missionTitle;

    @Column(nullable = false)
    private String missionCondition;

    @Column(nullable = false)
    private Integer rewardPoint;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate; // 미션 진행 중이면 NULL 가능

    // 홈 화면 JPQL 생성자
    public Mission(Long id,
                   String missionTitle,
                   String missionCondition,
                   Integer rewardPoint,
                   Store store) {
        this.id = id;
        this.missionTitle = missionTitle;
        this.missionCondition = missionCondition;
        this.rewardPoint = rewardPoint;
        this.store = store;
    }
}
