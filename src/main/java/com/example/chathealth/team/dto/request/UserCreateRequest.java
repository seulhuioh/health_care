package com.example.chathealth.team.dto.request;

public class UserCreateRequest {
    private String name;
    private Integer height;
    private Integer weight;
    private String gender;

    public String getName() {
        return name;
    }


    public String getGender(){
        return gender;
    }


    public Integer getHeight() {
        return height;

    }

    public Integer getWeight() {
    return weight;
    }
}
//