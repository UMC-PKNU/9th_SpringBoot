package com.example.umc_9th_spring.domain.mission.entity;

import com.example.umc_9th_spring.domain.user.entity.UserMissionMap;
import com.example.umc_9th_spring.domain.store.entity.Store;
import com.example.umc_9th_spring.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "reward_point", nullable = false)
    private int rewardPoint;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserMissionMap> userMissionMaps = new ArrayList<>();

}
