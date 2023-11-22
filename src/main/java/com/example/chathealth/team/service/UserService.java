package com.example.chathealth.team.service;

import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import com.example.chathealth.team.dto.request.UserCreateRequest;
import com.example.chathealth.team.dto.request.UserUpdateRequest;
import com.example.chathealth.team.dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }





    @Transactional
    public void saveUser(UserCreateRequest request){
        User user = userRepository.save(new User(request.getName(), request.getAge()));

    }
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {

        // select * from user where id = ?
        //Optional<User> 유저가 있으면 결과, 없으면 예외
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);
        user.updateName(request.getName()); // 유저의 객체 가져와서 업데이트(User.updateName )

    }
    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }



}
