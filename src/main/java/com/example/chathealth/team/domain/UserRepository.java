package com.example.chathealth.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
    Optional<User> findById(Long id);
    Optional<User> findByHeight(int height);

    Optional<User>findAllById(Long id);

}
