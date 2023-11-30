package com.example.chathealth.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findAllByUser(User user);
    Optional<Team> findByTeamName(String TeamName);
    void deleteByTeamName(String TeamName);
    void deleteById(Long id);

}
