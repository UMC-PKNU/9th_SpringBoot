package com.example.umc_9th_.domain.user.repository;

import com.example.umc_9th_.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // JPQL로 마이페이지 기본 정보 조회
    @Query("""
        SELECT new User(
            u.id, u.nickname, u.email, u.phoneNum, u.isPhoneNumVer, u.userPoint
        )
        FROM User u
        WHERE u.id = :userId
    """)
    Optional<User> findUserMyPageInfo(@Param("userId") Long userId);

    Optional<User> findByEmail(String email);
}
