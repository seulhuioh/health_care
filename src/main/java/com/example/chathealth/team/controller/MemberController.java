package com.example.chathealth.team.controller;


import com.example.chathealth.team.dto.request.MemberCreateRequest;
import com.example.chathealth.team.dto.request.MemberUpdateRequest;
import com.example.chathealth.team.dto.response.MemberResponse;
import com.example.chathealth.team.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Member CRUD API
    // Member To Team first
    //User To Member second

    //Create
    @PostMapping("/member")
    public void saveMember(@RequestBody MemberCreateRequest request){
        memberService.saveMember(request);

    }


    //Read
    @GetMapping ("/member")
    public List<MemberResponse> getMember(){
        return memberService.getMembers();
    }

    //UPdate
    @PutMapping("/member")
    public void updateMember(@RequestBody MemberUpdateRequest request){
        memberService.updateMember(request);
    }


    //Delete
    @DeleteMapping("/member")
    public void deleteMember(@RequestParam String name) {
        memberService.deleteMember(name); // Get UserId From Member Table

    }






}
