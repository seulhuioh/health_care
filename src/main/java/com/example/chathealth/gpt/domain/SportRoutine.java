package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "SportRoutine")
public class SportRoutine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;
    @ManyToOne //매핑
    private User user = new User();


    @Column(nullable = true, name ="sport_routine" )
    private String  SportRoutine =null;

    //constructor
    protected SportRoutine() {
    } // 기본 생성자 -> 새로운 DB삽입시 필요


    public SportRoutine(Long Id){
        if(Id == null){
            throw new IllegalArgumentException(String.format("존재하지 않는 루틴 "));
        }
        this.Id =Id;

    }


    public Long getId() {
        return Id;
    }

    public User getUser() {
        return user;
    }

    public String getSportRoutine() {
        return SportRoutine;
    }
}
