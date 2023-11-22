package com.example.chathealth.team.controller;

import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.service.TeamService;
import com.example.chathealth.team.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    // 생성자 주입 (생략 가능)
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

   @PostMapping("/api/teams")
   public ResponseEntity<Team> createTeam(@RequestBody Team team)
   {
       return ResponseEntity.ok(teamService.createTeam(team));
    }

    // 전체 팀 목록 조회
    @GetMapping("/api/teams")
    public List<Team> getAllTeams() {

        return teamService.getAllTeams();
    }

    // 특정 팀의 사용자 목록 조회
    @GetMapping("/{teamName}/users")
    public List<User> getUsersByTeamName(@RequestParam String teamName) {
        List<User> users = teamService.getUsersByTeamName(teamName);
        return teamService.getUsersByTeamName(teamName);
    }

}