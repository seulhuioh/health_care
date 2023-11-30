package com.example.chathealth.team.dto.request;

import com.example.chathealth.team.domain.Member;
import com.example.chathealth.team.domain.User;

public class TeamUpdateRequest {
    private String TeamName;
    private String userName;
    private User user;
    private Member member;
    public String getTeamName() {
        return TeamName;
    }
    public String getUserName() {
        return userName;
    }
    public User getUser() {
        return user;
    }
    public Member getMember() {
        return member;
    }


}
