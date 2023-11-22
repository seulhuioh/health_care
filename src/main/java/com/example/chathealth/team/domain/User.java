package com.example.chathealth.team.domain;

import com.example.chathealth.gpt.domain.DailyHistory;
import com.example.chathealth.gpt.domain.Diet;
import com.example.chathealth.gpt.domain.SportRoutine;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;

    ///스포츠 루틴
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //연관 관계의 주인이 아님을 명시

    private List<SportRoutine> sportRoutines = new ArrayList<>();
    //팀
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //연관 관계의 주인이 아님을 명시
    private List<Team> teams = new ArrayList<>();
    //멤버
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //연관 관계의 주인이 아님을 명시
    private List<Member> members= new ArrayList<>();
    //식단
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //연관 관계의 주인이 아님을 명시
    private List<Diet> diets = new ArrayList<>();

    //일일 기록
    @OneToMany(mappedBy = "user") //연관 관계의 주인이 아님을 명시
    private List<DailyHistory> DailyHistories = new ArrayList<>();

    @Column(nullable = false, length = 20,name ="name" )//name VARCHAR(20)
    private String name;
    @Column(nullable = false, length = 50 )//name VARCHAR(20)
    private String nickname;
    @Column(nullable = false, length = 20)//name VARCHAR(20)
    private String gender;
    private Integer age;

    public User(){
    } // 기본 생성자

    public User(String name,Integer age){
        if(name ==null|| name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 이름: (%s)", name));

        }

        this.name=name;
        this.age=age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return Id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public void updateName(String name){

        this.name =name;
    }


}
