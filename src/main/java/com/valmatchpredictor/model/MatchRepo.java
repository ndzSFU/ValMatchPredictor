package com.valmatchpredictor.model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepo extends JpaRepository<Match, Long> {
    List<Match> findByteam1(String team1);

}