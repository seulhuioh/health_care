package com.example.chathealth.team.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "team")
    private List<Team> teams= new ArrayList<>();
    @OneToMany
    private List<User> users= new ArrayList<>();

    @Column(nullable = false, length = 200,name ="team_name" )
    private String TeamName;

    protected Team() {

    }
    public Team(String teamName) {
    }

    public Long getId() {
        return Id;
    }

    public String getTeamName() {

        return TeamName;
    }

    public List<User> getUsers() {
        return users;
    }
}
