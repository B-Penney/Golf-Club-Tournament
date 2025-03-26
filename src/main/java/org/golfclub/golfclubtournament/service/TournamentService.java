package org.golfclub.golfclubtournament.service;

import org.golfclub.golfclubtournament.model.Member;
import org.golfclub.golfclubtournament.model.Tournament;
import org.golfclub.golfclubtournament.repository.MemberRepository;
import org.golfclub.golfclubtournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository, MemberRepository memberRepository) {
        this.tournamentRepository = tournamentRepository;
        this.memberRepository = memberRepository;
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament getTournamentById(Long tournamentId) {
        return tournamentRepository.findById(tournamentId).orElse(null);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public List<Tournament> getTournamentByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> getTournamentByStartDate(LocalDate startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public Tournament updateTournament(Long id, Tournament updated) {
        return tournamentRepository.findById(id).map(tournament -> {
            tournament.setStartDate(updated.getStartDate());
            tournament.setEndDate(updated.getEndDate());
            tournament.setLocation(updated.getLocation());
            tournament.setEntryFee(updated.getEntryFee());
            tournament.setCashPrize(updated.getCashPrize());
            return tournamentRepository.save(tournament);
        }).orElse(null);
    }

    public boolean deleteTournament(Long id) {
        if (tournamentRepository.existsById(id)) {
            tournamentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Tournament addMemberToTournament(Long tournamentId, Long memberId) {
        Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
        Member member = memberRepository.findById(memberId).orElse(null);
        if (tournament != null && member != null) {
            tournament.getMembers().add(member);
            return tournamentRepository.save(tournament);
        }
        return null;
    }
}
