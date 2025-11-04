    package com.example.umc.domain.member.repository;

    import com.example.umc.domain.member.entity.mapping.MemberMission;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;

    import java.util.List;

    public interface MemberMissionRepository extends JpaRepository<MemberMission,Long> {

        // 미션4
        @Query("SELECT mm.status, (mm.status = :status), m.moneyLowerLimit, m.point, s.name " +
                "FROM MemberMission mm " +
                "JOIN mm.mission m " +
                "JOIN m.store s " +
                "ORDER BY mm.id DESC")
        List<Object[]> findMemberMissionsWithStatus(@Param("status") String status, Pageable pageable);
    }
