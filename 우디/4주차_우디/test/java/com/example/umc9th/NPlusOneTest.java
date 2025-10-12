package com.example.umc9th;

import com.example.umc9th.domain.pratice.Member2;
import com.example.umc9th.domain.pratice.MemberRepository2;
import com.example.umc9th.domain.pratice.Team;
import com.example.umc9th.domain.pratice.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
public class NPlusOneTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository2 memberRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        Team teamA = new Team("Team A");
        Team teamB = new Team("Team B");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        memberRepository.save(new Member2("User1", teamA));
        memberRepository.save(new Member2("User2", teamA));
        memberRepository.save(new Member2("User3", teamB));
        memberRepository.save(new Member2("User4", teamB));

        entityManager.flush();
        entityManager.clear();
        System.out.println("\n-- 데이터 준비 완료 ---");
    }

    @Test
    @DisplayName("N+1 문제 테스트")
    void nPlusOneProblemTest() {
        // given
        List<Member2> members = memberRepository.findAll();

        // when & then
        for (Member2 member : members) {
            System.out.println("Member: " + member.getUsername() + ", Team: " + member.getTeam().getName());
        }

        System.out.println("--- 루프 종료 ---");
    }

}
