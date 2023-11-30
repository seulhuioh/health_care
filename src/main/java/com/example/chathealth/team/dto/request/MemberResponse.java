package com.example.chathealth.team.dto.request;

public class MemberResponse {
    private Long Id;
    private Long userId;
    private Long teamId;
    private int UserRank;


    public MemberResponse(long Id, long userId, long teamId, int UserRank){
        this.Id = Id;
        this.userId = userId;
        this.teamId = teamId;
        this.UserRank = UserRank;

    }
    public MemberResponse(){

    }

    public Long getId() {
        return Id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public int getUserRank() {
        return UserRank;
    }
}
