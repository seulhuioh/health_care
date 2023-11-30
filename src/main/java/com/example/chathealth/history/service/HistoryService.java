package com.example.chathealth.history.service;

import com.example.chathealth.history.domain.CountHistory;
import com.example.chathealth.history.domain.CountHistoryRepository;
import com.example.chathealth.history.domain.DailyHistory;
import com.example.chathealth.history.domain.DailyHistoryRepository;
import com.example.chathealth.history.dto.CountHistoryResponse;
import com.example.chathealth.team.domain.User;
import com.example.chathealth.team.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    @Autowired
    private final CountHistoryRepository countHistoryRepository;
    private final UserRepository userRepository;

    public HistoryService
            (CountHistoryRepository countHistoryRepository,
             UserRepository userRepository) {
        this.countHistoryRepository = countHistoryRepository;
        this.userRepository = userRepository;
    }
/*
    public void saveCountHistory(Long userId, CountHistoryResponse response) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
        CountHistory countHistory = new CountHistory(
                response.getSetCount(),
                response.getSportCount(),
                response.getVolume(),
                response.getWorkOutTime()
                ) {
         countHistoryRepository.save(countHistory);
        }
        DailyHistory dailyHistory = DailyHistoryRepository.findByUser(user);


   }*/

//    public List<DailyHistory> getDailyHistory(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
//        //Time Stamp
//        return dailyHistoryRepository.findAllByUser(user).stream().collect(Collectors.toList());
//    }
}


