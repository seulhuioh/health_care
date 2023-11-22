package com.example.chathealth.team.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long Id =null;



    @ManyToOne //매핑
    private User user = new User();
    @ManyToOne //매핑
    private Team team = new Team();

    @Column(nullable = false, length = 200,name ="team_id" )
    private Long TeamId =null;
    @Column(nullable = true, length = 200,name ="user_rank" )
    private Integer UserRank = null;

    protected Member() {

    }

    public Long getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }

    public Long getTeamId() {
        return TeamId;
    }

    public int getUserRank() {
        return UserRank;
    }
}
