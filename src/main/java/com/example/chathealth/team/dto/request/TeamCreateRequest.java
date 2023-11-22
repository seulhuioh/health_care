package com.example.chathealth.team.dto.request;


// DTO는 API 스펙이 중요 -> 조회 할때 쓸 필드와 getter 있어야함 -> 컨트롤러에서 매개변수로 넣어주자
public class TeamCreateRequest {
    private String TeamName;

    public String getTeamName() {
        return TeamName;
    }


}
