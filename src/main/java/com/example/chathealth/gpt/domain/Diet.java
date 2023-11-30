package com.example.chathealth.gpt.domain;


import com.example.chathealth.team.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "diet")
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id =null;
    @ManyToOne //매핑
    private User user ;

    @Column(nullable = true,name ="diet" )
    private String  Diet = null;

    public Diet() {

    } // 기본 생성자 -> 새로운 DB삽입시 필요

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getDiet() {
        return Diet;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDiet(String diet) {
        Diet = diet;
    }

}
