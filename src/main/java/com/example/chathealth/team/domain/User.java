package com.example.chathealth.team.domain;

import com.example.chathealth.history.domain.DailyHistory;
import com.example.chathealth.gpt.domain.Diet;
import com.example.chathealth.gpt.domain.SportRoutine;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;

    ///스포츠 루틴
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)

    private List<SportRoutine> sportRoutines = new ArrayList<>();
    //팀
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Team> teams = new ArrayList<>();
    @ManyToOne
    private Team team;
    //멤버
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Member> members= new ArrayList<>();
    //식단
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Diet> diets = new ArrayList<>();

    //일일 기록
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DailyHistory> DailyHistories = new ArrayList<>();

    @Column(nullable = false, length = 20,name ="name" )//name VARCHAR(20)
    private String name;

    @Column(nullable = false, length = 20)//name VARCHAR(20)
    private String gender;
    @Getter
    @Column(nullable = false)//height
    private Integer height;
    @Getter
    @Column(nullable = false)//weight
    private Integer weight;

    public User(){
    } // 기본 생성자

    public User(String name){
        if(name ==null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 이름: (%s)", name));

        }

        this.name=name;
    }

    public User(String name, String gender, Integer weight, Integer height) {
        if(name ==null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 이름: (%s)", name));

        }
        if(gender ==null|| gender.isBlank()){
            throw new IllegalArgumentException(String.format("성별을 입력하세요"));

        }
if(weight ==null|| weight<0){
            throw new IllegalArgumentException(String.format("몸무게를 입력하세요"));

        }
if(height ==null|| height<0){
            throw new IllegalArgumentException(String.format("키를 입력하세요"));

        }
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;


    }

    public String getName() {
        return name;
    }


    public Long getId() {
        return id;
    }

    public List<SportRoutine> getSportRoutines() {
        return sportRoutines;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public List<DailyHistory> getDailyHistories() {
        return DailyHistories;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getGender() {return gender;}

    public void updateName(String name){

        this.name =name;
    }


}
