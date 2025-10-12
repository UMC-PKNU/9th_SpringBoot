package com.example.umc9th.domain.pratice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository2 extends JpaRepository<Member2, Long> {

    @Query("select m from Member2 m join fetch m.team")
    List<Member2> findAll();
}
