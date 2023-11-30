package com.example.chathealth.gpt.service;


import com.example.chathealth.gpt.domain.*;
import com.example.chathealth.gpt.dto.request.SportRoutineCreateRequest;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;



@Service
public class SportRoutineService {
    private final SportRoutineRepository sportRoutineRepository;
    private final SportRoutineListRepository sportRoutineListRepository;
    private final UserRepository userRepository;
    private final SportCategoryRepository sportCategoryRepository;

    public SportRoutineService(
            SportRoutineRepository sportRoutineRepository,
            SportRoutineListRepository sportRoutineListRepository,
            UserRepository userRepository,
            SportCategoryRepository sportCategoryRepository)
    {
        this.sportRoutineRepository = sportRoutineRepository;
        this.sportRoutineListRepository = sportRoutineListRepository;
        this.userRepository = userRepository;
        this.sportCategoryRepository = sportCategoryRepository;
    }
    //////


    @Transactional
    public SportRoutine createSportRoutine(Long userId, SportRoutineCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        SportRoutine sportRoutine = new SportRoutine(user,request.getName());
        sportRoutine = sportRoutineRepository.save(sportRoutine);

        for (SportRoutineCreateRequest.Detail detail : request.getDetails()) {
            Integer sportCategoryId = Integer.parseInt(detail.getId());
            SportCategory sportCategory = sportCategoryRepository.findById(sportCategoryId)
                    .orElseThrow(() -> new EntityNotFoundException("SportCategory not found"));

            SportRoutineList sportRoutineList =
                    new SportRoutineList(
                            sportRoutine, sportCategory,
                            Integer.parseInt(detail.getReps()),
                            Integer.parseInt(detail.getSets()),
                            Integer.parseInt(detail.getVolume()));

            sportRoutineListRepository.save(sportRoutineList);
        }

        return sportRoutine;
    }

    @Transactional
    public void deleteSportRoutine(Long userId, Long sportRoutineId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        SportRoutine sportRoutine = sportRoutineRepository.findById(sportRoutineId)
                .orElseThrow(() -> new EntityNotFoundException("SportRoutine not found"));

        if (!sportRoutine.getUser().equals(user)) {
            throw new IllegalArgumentException("User does not own this SportRoutine");
        }
        sportRoutineRepository.delete(sportRoutine); // 비즈니스 요구 사항(도메인 단계에서 구현) : 루틴 삭제 시 루틴에 포함된 운동도 삭제

    }


}
