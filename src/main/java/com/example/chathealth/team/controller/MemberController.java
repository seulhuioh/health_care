package com.example.chathealth.team.controller;

import com.example.chathealth.gpt.domain.SportRoutine;
import com.example.chathealth.team.domain.*;
import io.swagger.annotations.ApiOperation;
import com.example.chathealth.team.dto.response.MemberResponse;
import com.example.chathealth.team.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    public MemberController(
            MemberService memberService,
            TeamRepository teamRepository,
            MemberRepository memberRepository,
            UserRepository userRepository) {
        this.memberService = memberService;
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    // Member CRUD API

    //Create
    // 멤버 추가
   @PostMapping("/add/{teamId}/{userId}")
  // @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Member> addMember(@PathVariable Long teamId, @PathVariable Long userId) {
       try {
           Team team = teamRepository.findById(teamId)
                   .orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

           Member newMember = new Member(user, team);

           return ResponseEntity.ok(newMember);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }


    //Read - 멤버 찾기
    @GetMapping("/find-all/{teamId}/member-list")
    public ResponseEntity<List<Member>> getMember(@PathVariable Long teamId) {
        try {
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));

            List<Member> members = memberRepository
                    .findAllByTeam(team).stream().collect(Collectors.toList());

            return ResponseEntity.ok(members);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Delete
    // 멤버 삭제 - 그룹 탈퇴 시
    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
