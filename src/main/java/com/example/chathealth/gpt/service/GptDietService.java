package com.example.chathealth.gpt.service;

import com.example.chathealth.gpt.domain.SportRoutineRepository;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GptDietService {

    private final UserRepository userRepository;
    private final SportRoutineRepository sportRoutineRepository;


    // constructor that need some fields.
    public GptDietService(
            UserRepository userRepository,
            SportRoutineRepository sportRoutineRepository) {
        this.userRepository = userRepository;
        this.sportRoutineRepository = sportRoutineRepository;
    }


    //사용자 DB 정보 정제 -> prompt 의 intput으로 들어갈 것
    // you need to add identity in User table
    public String getUserWeightAndHeight(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

         return "Weight: " + user.getWeight() + ", Height: " + user.getHeight();
    }




}
