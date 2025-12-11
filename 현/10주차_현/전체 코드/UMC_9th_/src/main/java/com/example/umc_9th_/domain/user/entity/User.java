package com.example.umc_9th_.domain.user.entity;

import com.example.umc_9th_.domain.base.entity.BaseEntity;
import com.example.umc_9th_.domain.review.entity.Review;
import com.example.umc_9th_.domain.user.enums.Gender;
import com.example.umc_9th_.domain.user.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "user")
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(unique = true, length = 20)
    private String phoneNum;

    @Builder.Default
    private Boolean isPhoneNumVer = false;

    @Builder.Default
    private Integer userPoint = 0;

    @Builder.Default
    private Boolean userState = true;

    @Enumerated(EnumType.STRING)
    private Role role; // 권한 (USER, ADMIN)

    // 회원가입용 포인트 초기화 및 권한 설정 메서드 (빌더나 생성자에 포함하거나 별도 설정)
    public void encodePassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private LocalDateTime inactiveDate;

    public void addPoint(Integer point) {
        this.userPoint += point;
    }

    // 마이페이지 전용 생성자 (JPQL new 구문용)
    public User(Long id, String nickname, String email, String phoneNum, Boolean isPhoneNumVer, Integer userPoint) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phoneNum = phoneNum;
        this.isPhoneNumVer = isPhoneNumVer;
        this.userPoint = userPoint;
    }

    // 연관관계
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserMission> userMissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserPreference> userPreferences;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserTerms> userTerms;
}
