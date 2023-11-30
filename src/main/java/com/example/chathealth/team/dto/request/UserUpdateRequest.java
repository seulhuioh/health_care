package com.example.chathealth.team.dto.request;

public class UserUpdateRequest {
    private  long id;
    private String name;
    public  long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
