package com.example.chathealth.gpt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRoutineListRepository extends JpaRepository<SportRoutineList,Long> {
    Optional<SportRoutineList> findById(Long Id);


}
