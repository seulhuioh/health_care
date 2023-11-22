package com.example.chathealth.gpt.domain;


import com.example.chathealth.team.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id =null;
    @ManyToOne //매핑
    private User user = new User();
    @Column(nullable = true,name ="user_id" )
    private Long  UserId =null;

    @Column(nullable = true,name ="diet" )
    private String  Diet = null;

    protected Diet() {

    } // 기본 생성자 -> 새로운 DB삽입시 필요

    public Long getId() {
        return Id;
    }

    public Long getUserId() {
        return UserId;
    }

    public String getDiet() {
        return Diet;
    }
}
