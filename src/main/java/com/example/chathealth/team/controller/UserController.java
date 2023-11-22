package com.example.chathealth.team.controller;

import com.example.chathealth.team.dto.request.UserCreateRequest;
import com.example.chathealth.team.dto.request.UserUpdateRequest;
import com.example.chathealth.team.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.chathealth.team.dto.response.UserResponse;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    //user CRUD API
    //Create
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }
    //Read
    @GetMapping("/user")
    public List<UserResponse> getUser(){

        return userService.getUsers();
    }
    //Update
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){

        userService.updateUser( request);
    }
    //Delete
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {

        userService.deleteUser(name);
    }
}
