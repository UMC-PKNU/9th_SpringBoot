package com.example.umc9th.domain.member.entity;
import com.example.umc9th.domain.review.entity.Review;
//import com.example.umc9th.domain.member.entity.mapping.MemberFood;
import com.example.umc9th.domain.member.entity.mapping.MemberMission;
import com.example.umc9th.domain.member.enums.Gender;
//import com.example.umc9th.domain.store.enums.Address;
//import com.example.umc9th.global.auth.enums.SocialType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name= "member")
@EntityListeners(AuditingEntityListener.class)

public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", length = 20, nullable = false, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "point")
    @Builder.Default
    private Long point = 0L;

    @Column(name = "last_login_at")
    private LocalDateTime last_login_at;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private boolean is_active = true;

    @Column(name = "inactive_date")
    private LocalDateTime inactive_date;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "phoneNumber", length = 13, nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "local", nullable = false)
    private String local;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<Review> reviewList = new ArrayList<>();


}