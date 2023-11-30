package com.example.chathealth.team.dto.request;


import com.example.chathealth.team.domain.Team;
import com.example.chathealth.team.domain.User;

// DTO는 API 스펙이 중요 -> 조회 할때 쓸 필드와 getter 있어야함 -> 컨트롤러에서 매개변수로 넣어주자
public class TeamCreateRequest {
    private String TeamName;
    private Long Head;
    private int memberNum;

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    private User user = new User();
    
    public TeamCreateRequest() {
    }

    public User getUser() {
        return user;
    }

    public String getTeamName() {
        return TeamName;
    }
    public Long getHead() {
        return Head;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
    public void setHead(Long head) {
        this.Head = head;
    }


    public Integer getMemberNum() {
        return memberNum;
    }
}
