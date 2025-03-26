package org.golfclub.golfclubtournament.repository;

import org.golfclub.golfclubtournament.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNameContainingIgnoreCase(String name);
    List<Member> findByPhoneNum(String phoneNum);
    List<Member> findByStartDate(LocalDate startDate);
    List<Member> findByTournaments_StartDate(LocalDate tournamentStartDate);
}

