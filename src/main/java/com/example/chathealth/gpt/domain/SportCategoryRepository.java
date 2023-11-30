package com.example.chathealth.gpt.domain;

import com.example.chathealth.team.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportCategoryRepository extends JpaRepository<SportCategory ,Long> {


    Optional<SportCategory> findBySportName(String sportName);



    Optional<SportCategory> findById(Integer sportCategoryId);
}
