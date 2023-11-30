package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRoutineListRepository extends JpaRepository<SportRoutineList,Long> {

    Optional<SportRoutineList> findAllBySportRoutine( SportRoutine sportRoutine);



}
