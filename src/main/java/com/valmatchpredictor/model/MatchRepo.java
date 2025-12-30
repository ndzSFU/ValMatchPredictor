package com.valmatchpredictor.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {
    List<Match> findByteam1(String team1);

    @Query("SELECT m FROM Match m WHERE m.team1 = :teamName OR m.team2 = :teamName")
    List<Match> findMatches(@Param("teamName") String teamName);

}