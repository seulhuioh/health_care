package com.example.chathealth.gpt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountHistoryRepository extends JpaRepository<CountHistory,Long> { //엔티티 이름과  Id타입


    Optional<CountHistory> findBySportCategory(SportCategory sportCategory);


}
