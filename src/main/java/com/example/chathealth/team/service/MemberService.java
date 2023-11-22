package com.example.chathealth.team.service;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.MemberRepository;
import com.example.chathealth.team.domain.TeamRepository;
import com.example.chathealth.team.domain.UserRepository;
import com.example.chathealth.team.dto.request.MemberCreateRequest;
import com.example.chathealth.team.dto.response.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;


@Service
public class MemberService {

    private final TeamRepository teamRepository ;
    private final MemberRepository memberRepository ;
    private final UserRepository userRepository;

    //
    public  MemberService(){
    }
    //constructor
    public MemberService(
            MemberRepository memberRepository,
            TeamRepository teamRepository,
            UserRepository userRepository) {

        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public void saveMember(MemberCreateRequest request){
    Member member = memberRepository.save(new Member(request.getUserId(),request.getTeamId()));

    }
    @Transactional(readOnly = true)
    public List<Member> getMembers() {
        return memberRepository.findAll().stream()
                .map(MemberResponse::new))
                .collect(Collectors.toList());
    }



    @Transactional
    public void updateMember(){


    }
    @Transactional
    public void deleteMember(){


    }

    //read

}
