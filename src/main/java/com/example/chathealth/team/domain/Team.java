package com.example.chathealth.team.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;

    @Column(nullable = false, length = 200,name ="member_num" )
    private Integer memberNum = null;

    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "team") //매핑되는 엔티티의 필드명
    private List<User> users= new ArrayList<>();

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // automatically delete,create member when team is deleted, or created
    private List<Member> members= new ArrayList<>();
    @Column(nullable = false, length = 200,name ="team_name" )
    private String teamName;
    @Getter
    @Column(nullable = false, length = 200,name ="head" )
    private Long head;


    public Team() {

    }

    //need team make method
    public Team(String teamName , int memberNum, Long head, User user) {
        if(teamName ==null|| teamName.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 이름: (%s)", teamName));

        }
        this.teamName=teamName;
        this.head=head;
        this.memberNum=memberNum;
    }

    public Long getId() {
        return id;
    }

    public String getTeamName() {return teamName;}

    public User getUser() {
        return user;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Long getHead() {
        return head;
    }

    public List<User> getUsers() {
        return users;
    }

    public Integer getMemberNum() {return memberNum;}

    public void setMemberNum(Integer memberNum) {this.memberNum = memberNum;}

    public void setUser(User user) {
        this.user = user;
    }

    public void setTeamName(String teamName) {
        teamName = teamName;
    }

    public void setHead(Long head) {
        head = head;
    }


}
