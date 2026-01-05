package com.valmatchpredictor.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

//REPRESENTS A COMPLETE MATCHUP SERIES BETWEEN 2 TEAMS
@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String tournament_name;
    @JsonProperty("match_page")
    private String matchUrl;
    @JsonIgnore
    private LocalDateTime matchDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id")
    private List<MatchMap> matchMaps;

    public List<MatchMap> getMaps() {
        return matchMaps;
    }

    public void setMaps(List<MatchMap> matchMaps) {
        this.matchMaps = matchMaps;
    }

    // Getters
    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getScore1() {
        return score1;
    }

    public String getScore2() {
        return score2;
    }

    public LocalDateTime getMatchDate() {
        return matchDate;
    }

    // Setters
    public void setMatchDate(LocalDateTime matchDate) {this.matchDate = matchDate;}

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getMatchUrl() {
        return matchUrl;
    }

    public void setMatchUrl(String matchUrl) {
        this.matchUrl = matchUrl;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
