package com.example.umc9th_week4.domain.member.entity;

import com.example.umc9th_week4.domain.member.enums.Gender;
import com.example.umc9th_week4.domain.member.enums.MemberStatus;
import com.example.umc9th_week4.domain.mission.entity.MemberMission;
import com.example.umc9th_week4.domain.review.entity.StoreReview;
import com.example.umc9th_week4.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "member", fetch=FetchType.LAZY)
    @Builder.Default
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<StoreReview> storeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    @Builder.Default
    private List<MemberFoodCategory> memberFoodCategoryList = new ArrayList<>();

    @Column(name="name", nullable = false, length=3)
    private String name;

    @Column(name="gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth", nullable = false)
    private LocalDate birth;

    @Column(name="address", nullable=false)
    private String address;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column(name="inactive_date")
    private LocalDateTime inactiveDate;

    @Column(name="points", nullable = false)
    @Builder.Default
    private Long points=0L;
}
