package org.golfclub.golfclubtournament.service;

import org.golfclub.golfclubtournament.model.Member;
import org.golfclub.golfclubtournament.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member newMember) {
        return memberRepository.save(newMember);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // Uses repository method for searching by name.
    public List<Member> getMembersByName(String name) {
        return memberRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Member> getMembersByPhoneNumber(String phoneNum) {
        return memberRepository.findByPhoneNum(phoneNum);
    }

    public List<Member> getMembersByStartDate(LocalDate startDate) {
        return memberRepository.findByStartDate(startDate);
    }

    public List<Member> getMembersByTournamentStartDate(LocalDate tournamentStartDate) {
        return memberRepository.findByTournaments_StartDate(tournamentStartDate);
    }
    public Member getById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member updateMember(Long id, Member updated) {
        return memberRepository.findById(id).map(member -> {
            member.setName(updated.getName());
            member.setAddress(updated.getAddress());
            member.setEmail(updated.getEmail());
            member.setPhoneNum(updated.getPhoneNum());
            member.setStartDate(updated.getStartDate());
            member.setDuration(updated.getDuration());
            return memberRepository.save(member);
        }).orElse(null);
    }

    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
