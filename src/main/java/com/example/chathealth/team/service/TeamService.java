package com.example.chathealth.team.service;

import com.example.chathealth.team.domain.*;
import com.example.chathealth.team.dto.request.TeamCreateRequest;
import com.example.chathealth.team.dto.request.TeamUpdateRequest;
import com.example.chathealth.team.dto.response.TeamDeleteResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;


    @Autowired
    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }


    // team make method
    @Transactional
    public void createTeam( TeamCreateRequest request, Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        // 새 팀 객체 생성, 여기서 'head' 필드에 사용자 ID 저장
        // 새 팀 객체 생성, 여기서 'head' 필드에 사용자 ID 저장
        Team team = new Team(request.getTeamName(), request.getMemberNum(),request.getHead() ,user);
         // 사용자 ID를 int 형태로 변환하여 'head'에 저장
        // 팀 저장
         teamRepository.save(team);

        // 멤버 객체 생성 및 저장
        Member member = new Member(user, team);
        memberRepository.save(member);

    }

    @Transactional(readOnly = true)// 현재 사용자가 속한 모든 팀 조회
    public List<Team> getTeamsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자을 찾을 수 없습니다."));
        return teamRepository.findAllByUser(user).stream().collect(Collectors.toList());
    }
    @Transactional(readOnly = true) // 팀의 모든 멤버 조회
    public List<Member> getTeamMembers(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("팀을 찾을 수 없습니다."));
        return memberRepository.findAllByTeam(team)
                .stream().collect(Collectors.toList());
    }


    @Transactional   // 팀 삭제
    public void deleteTeam(Long teamId) {
        memberRepository.deleteByTeamId(teamId);
        teamRepository.deleteById(teamId);
    }

    @Transactional // 팀 업데이트 (새로운 멤버 추가)
    public void updateTeam(Long teamId,TeamUpdateRequest request) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("팀을 찾을 수 없습니다."));
        User newUser = userRepository.findByName(request.getUserName())
                .orElseThrow(() -> new EntityNotFoundException("새로운 사용자를 찾을 수 없습니다."));

        Member newMember = new Member(newUser, team);
        memberRepository.save(newMember);

    }

    // 특정 팀의 사용자 목록 조회
    @Transactional(readOnly = true)
   public List<User> getUsersByTeamId(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."))
                .getUsers();
    }

    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }



}
