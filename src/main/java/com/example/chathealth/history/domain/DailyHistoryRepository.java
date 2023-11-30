package com.example.chathealth.history.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailyHistoryRepository extends JpaRepository<DailyHistory,Long>{


    Optional<DailyHistory> findByUser(String user);
    Optional<DailyHistory> findByDate(String date);
    Optional<DailyHistory> findAllByUser(String user);
}
