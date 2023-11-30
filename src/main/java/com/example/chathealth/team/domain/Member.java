package com.example.chathealth.team.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id =null;
    @ManyToOne //매핑
    private User user = new User();
    @ManyToOne //매핑
    private Team team = new Team();

    public Member() {
    }


    public Member(User user, Team team){
        this.user=user;
        this.team=team;
    }



    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Team getTeam() {
        return team;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
