package com.example.chathealth.team.service;

import com.example.chathealth.team.domain.MemberRepository;
import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.domain.TeamRepository;
import com.example.chathealth.team.dto.request.TeamCreateRequest;
import com.example.chathealth.team.dto.request.TeamUpdateRequest;
import com.example.chathealth.team.dto.response.TeamDeleteResponse;

import com.example.chathealth.team.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    public TeamService() { //need  field constructor , this , private final
        teamRepository = null;
        memberRepository = null;
    }

    public TeamService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }


    // team make method
    @Transactional
    public void createTeam(TeamCreateRequest request) {
        teamRepository.save(new Team(request.getTeamName()));
    }

    @Transactional
    public void deleteTeam(TeamDeleteResponse request) {
        teamRepository.delete(new Team(request.getTeamName()));
    }


    //
    @Transactional
public void updateTeam(TeamUpdateRequest request) {
        //update member
        //if member is not exist -> add member
        //teamRepository.save(new Member(request.getMemberName()));
    }


    // 특정 팀의 사용자 목록 조회
    @Transactional(readOnly = true)
   public List<User> getUsersByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."))
                .getUsers();
    }


    @Transactional
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }





}
