package com.example.chathealth.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team,Long> {

    Optional<Team> findByTeamName(String TeamName);
}
