package com.example.chathealth.gpt.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DietRepository extends JpaRepository< Diet,Long> {


    Optional<Diet> findById(Long Id);
}
