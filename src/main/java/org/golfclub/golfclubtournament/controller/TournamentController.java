package org.golfclub.golfclubtournament.controller;

import org.golfclub.golfclubtournament.model.Tournament;
import org.golfclub.golfclubtournament.service.TournamentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Tournament tournament = tournamentService.getTournamentById(id);
        return tournament != null ? ResponseEntity.ok(tournament) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament updatedTournament) {
        Tournament tournament = tournamentService.updateTournament(id, updatedTournament);
        return tournament != null ? ResponseEntity.ok(tournament) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        boolean deleted = tournamentService.deleteTournament(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/{tournamentId}/members/{memberId}")
    public ResponseEntity<Tournament> addMemberToTournament(
            @PathVariable Long tournamentId, @PathVariable Long memberId) {
        Tournament tournament = tournamentService.addMemberToTournament(tournamentId, memberId);
        return tournament != null ? ResponseEntity.ok(tournament) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Tournament> searchTournaments(@RequestParam(required = false) String location) {
        return (location != null) ? tournamentService.getTournamentByLocation(location)
                : tournamentService.getAllTournaments();
    }
}

