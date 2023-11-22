package com.example.chathealth.team.dto.response;

import com.example.chathealth.team.domain.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse( long id, String name, Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
    public UserResponse( User user){
        this.id = id;
        this.name = user.getName();
        this.age = user.getAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
