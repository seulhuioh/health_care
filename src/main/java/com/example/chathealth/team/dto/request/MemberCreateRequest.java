package com.example.chathealth.team.dto.request;

public class MemberCreateRequest {
    private Long Id;
    private Long userId;
    private Long teamId;
    private int UserRank;


    public MemberCreateRequest(long Id, long userId, long teamId, int UserRank){
        this.Id = Id;
        this.userId = userId;
        this.teamId = teamId;
        this.UserRank = UserRank;

    }
    public MemberCreateRequest(){

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
