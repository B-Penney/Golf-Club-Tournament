package org.golfclub.golfclubtournament.controller;

import org.golfclub.golfclubtournament.model.Member;
import org.golfclub.golfclubtournament.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAll();
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Member member = memberService.getById(id);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember) {
        Member member = memberService.updateMember(id, updatedMember);
        return member != null ? ResponseEntity.ok(member) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean deleted = memberService.deleteMember(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Member> searchMembers(@RequestParam(required = false) String name) {
        return (name != null) ? memberService.getMembersByName(name) : memberService.findAll();
    }
}
