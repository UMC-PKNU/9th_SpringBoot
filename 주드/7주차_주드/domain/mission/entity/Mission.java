package com.example.umc9th.domain.mission.entity;


import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.store.entity.Store;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")


public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "name" ,length = 50, nullable = false)
    private String name;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @Column(name = "reward", nullable = false)
    private Long reward;

    @Column(name = "local", nullable = false)
    private String local;

    @OneToMany(mappedBy = "mission")
    @JsonIgnore
    private List<MemberMission> memberMissionList = new ArrayList<>();

}
