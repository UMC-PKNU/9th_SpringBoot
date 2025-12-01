package com.example.umc.domain.member.entity;

import com.example.umc.domain.member.entity.mapping.MemberFood;
import com.example.umc.domain.member.enums.Gender;
import com.example.umc.domain.member.enums.MemberStatus;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "member")
public class Member extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50)
    private String username;

//    @Column(nullable=false, length=50)
    private String nickname;

//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable=false, length=20)
    private String birth;

    @Column(nullable = false, length=50)
    private String address;

    @Column(nullable = false, length=5)
    private String position;

//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberStatus status;

//    @Column(nullable = false)
    private LocalDateTime inactiveAt;

    @Column(nullable = false)
    private int point = 0;

//    @Column(nullable = false, length=20)
    private String phoneNumber;

//    @Column(nullable=false, length=100)
    private String email;

//    @Column(nullable = false)
    private String password;

    @Column(nullable=false)
    private int successMissionCnt = 0;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<MemberMission> memberMissions = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<UserTerm> userTerms = new ArrayList<>();

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberFood> memberFoods = new ArrayList<>();
}

