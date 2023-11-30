package com.example.chathealth.team.service;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.MemberRepository;
import com.example.chathealth.team.domain.TeamRepository;
import com.example.chathealth.team.domain.UserRepository;
import com.example.chathealth.team.dto.request.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class MemberService {

    private final TeamRepository teamRepository ;
    private final MemberRepository memberRepository ;
    private final UserRepository userRepository;

    //
    public  MemberService(){
        teamRepository = null;
        memberRepository = null;
        userRepository = null;
    }
    //constructor
    public MemberService( // need 3 parameter
            MemberRepository memberRepository,
            TeamRepository teamRepository,
            UserRepository userRepository) {

        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }



    @Transactional
    public void addMember(Long userId, Long teamId){
        Member member = memberRepository.save(new Member(userRepository.findById(userId).orElseThrow(IllegalArgumentException::new),teamRepository.findById(teamId).orElseThrow(IllegalArgumentException::new)));

    }

    @Transactional(readOnly = true)
    public List<Member> getMember(Long memberId) { // !! need to update
        return memberRepository.findAll().stream().collect(Collectors.toList());

    }


    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    //read

}
