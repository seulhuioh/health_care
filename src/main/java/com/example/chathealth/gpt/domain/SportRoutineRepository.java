package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportRoutineRepository extends JpaRepository<SportRoutine,Long> {

    Optional<SportRoutine> findByUser(User user);


}
