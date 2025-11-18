package com.example.umc_9th_spring.domain.user.repository;

import com.example.umc_9th_spring.domain.review.entity.Review;
import com.example.umc_9th_spring.domain.inquiry.entity.Inquiry;
import com.example.umc_9th_spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //로그인 된 사용자의 마이페이지 초기 화면에서 닉네임과 같은 정보를 표현하기 위한 쿼리
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

}
