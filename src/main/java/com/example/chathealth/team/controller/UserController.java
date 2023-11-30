package com.example.chathealth.team.controller;

import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import com.example.chathealth.team.dto.request.UserCreateRequest;
import com.example.chathealth.team.dto.request.UserUpdateRequest;
import com.example.chathealth.team.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }

    //user CRUD API
    //Create


    @PostMapping("create/users")
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);
    }
    //Read
   // @RequestMapping(value = "/api",method = RequestMethod.GET)
    @GetMapping("find/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + userId));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //Update
  //  @RequestMapping(value = "api",method = RequestMethod.PUT)
    @PutMapping("update/users")

    public void updateUser(@RequestBody UserUpdateRequest request){

        userService.updateUser( request);
    }
    //Delete
  //  @RequestMapping(value = "api",method = RequestMethod.DELETE)
    @DeleteMapping("delete/users")
    public void deleteUser(@RequestParam Long id) {

        userService.deleteUser(id);
    }
//    @RequestMapping(value = "api",method = RequestMethod.DELETE)
    @GetMapping("/checkUserId/{userId}")
    public ResponseEntity<Boolean> checkUserId(@PathVariable Long userId) {
        boolean isDuplicated = userService.isUserIdDuplicated(userId);
        return ResponseEntity.ok(isDuplicated);
    }
}
