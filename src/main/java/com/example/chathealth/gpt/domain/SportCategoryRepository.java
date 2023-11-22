package com.example.chathealth.gpt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportCategoryRepository extends JpaRepository<SportCategory ,Long> {
    Optional<SportCategory> findById(Long Id);


}
