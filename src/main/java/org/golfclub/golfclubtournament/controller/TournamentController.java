package org.golfclub.golfclubtournament.controller;

import org.golfclub.golfclubtournament.model.Tournament;
import org.golfclub.golfclubtournament.repository.MemberRepository;
import org.golfclub.golfclubtournament.repository.TournamentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentRepository tournamentRepository;
    private final MemberRepository memberRepository;

    public TournamentController(TournamentRepository TournamentRepository, MemberRepository MemberRepository) {
        this.tournamentRepository = TournamentRepository;
        this.memberRepository = MemberRepository;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        return tournamentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(
            @PathVariable Long tournamentId, @PathVariable Long memberId) {
        Optional<Tournament> tournamentOpt = tournamentRepository.findById(tournamentId);
        Optional<Member> memberOpt = memberRepository.findById(memberId);
        if (tournamentOpt.isPresent() && memberOpt.isPresent()) {
            Tournament tournament = tournamentOpt.get();
            tournament.getMembers().add(memberOpt.get());
            tournamentRepository.save(tournament);
            return ResponseEntity.ok(tournament);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Tournament> searchTournaments(@RequestParam(required = false) String location) {
        if (location != null) {
            return tournamentRepository.findByLocationContainingIgnoreCase(location);
        }
        return tournamentRepository.findAll();
    }
}
