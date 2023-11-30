package com.example.chathealth.team.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findAllByTeam(Team team);

    void deleteByTeamId(Long teamId);


}
