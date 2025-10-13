package com.example.umc9th.domain.store.entity;

import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.review.entity.Review;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")


public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name" ,length = 50, nullable = false)
    private String name;

    @Column(name = "store_type", length = 20, nullable = false)
    private String store_type;

    @Column(name = "located_at", length = 20, nullable = false)
    private String located_at;

    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Mission> missionList = new ArrayList<>();


}
