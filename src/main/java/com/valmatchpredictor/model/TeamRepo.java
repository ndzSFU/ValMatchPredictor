package com.valmatchpredictor.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeamRepo extends JpaRepository<Team, Long> {
    Optional<Team> findByTeamName(String team1);

    @Query("SELECT t.logoURL FROM Team t WHERE t.teamName = :teamName")
    Optional<String> findLogoURLByTeamName(@Param("teamName") String teamName);

}

// Will be provided by the JPARepository implementation
//TeamMatchesRepo.save(team);       // insert or update
//TeamMatchesRepo.findById(id);      // find by primary key
//TeamMatchesRepo.findAll();         // get all Teams
//TeamMatchesRepo.delete(team);     // delete a match