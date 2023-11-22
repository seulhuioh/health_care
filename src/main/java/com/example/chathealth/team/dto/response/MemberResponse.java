package com.example.chathealth.team.dto.response;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.domain.User;

public class MemberResponse {
    private Long Id;
    private User user;
    private Team team;
    private int UserRank;


    public MemberResponse(long Id, User user, Team team, int UserRank){
        this.Id = Id;
        this.user = user;
        this.team = team;
        this.UserRank = UserRank;

    }
    public MemberResponse(Member member){
        this.Id = member.getId();
        this.user = member.getUser();
        this.team = member.getTeam();
        this.UserRank = member.getUserRank();

    }

    public Long getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() { // team variable instead of TeamId
        return team;
    }

    public int getUserRank() {
        return UserRank;
    }
}
