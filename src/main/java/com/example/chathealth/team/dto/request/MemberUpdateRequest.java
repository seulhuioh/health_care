package com.example.chathealth.team.dto.request;

public class MemberUpdateRequest {
    private Long Id;
    private Long userId;
    private Long teamId;
    private int UserRank;

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
