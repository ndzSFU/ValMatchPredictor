package com.valmatchpredictor.model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamMatchesRepo extends JpaRepository<Team, Long> {
    Optional<Team> findByteamName(String team1);

}

// Will be provided by the JPARepository implementation
//TeamMatchesRepo.save(team);       // insert or update
//TeamMatchesRepo.findById(id);      // find by primary key
//TeamMatchesRepo.findAll();         // get all Teams
//TeamMatchesRepo.delete(team);     // delete a match