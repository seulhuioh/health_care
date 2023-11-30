package com.example.chathealth.history.domain;
import com.example.chathealth.gpt.domain.SportCategory;
import com.example.chathealth.history.domain.CountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface CountHistoryRepository extends JpaRepository<CountHistory,Long>{


    Optional<CountHistory> findBySportCategory(SportCategory sportCategory);

    Optional<CountHistory> findByDailyHistory(DailyHistory dailyHistory);

}
