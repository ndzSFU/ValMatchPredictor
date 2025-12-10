package com.valmatchpredictor.controller;

import com.valmatchpredictor.model.*;
import com.valmatchpredictor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class PredictorController {

    private boolean isTier1(String teamName){
        switch(teamName){
            case "G2 Esports": return true;
            case "Sentinels": return true;
            case "NRG": return true;
            case "100 Thieves": return true;
            case "Evil Geniuses": return true;
            case "Cloud9": return true;
            case "LEVIATÁN": return true;
            default: return false;
        }
    }

    @Autowired
    private DataService dataService;

    @GetMapping("/teamMatches")
    public List<Match> showTeamMatches(String teamName){
        try{
            return dataService.ScrapeTeamMatches("G2 Esports");
        }
        catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // or handle error properly
        }
    }

    private int bannedMapModifier = 2;

    private int tallyWinRate(List<Map> teamMaps, String ban1, String ban2) {
        int addedScore = 0;

        for (Map map : teamMaps) {
            if(!Objects.equals(map.getName(), ban1) && !Objects.equals(map.getName(), ban2)) {
                addedScore += map.getMatchWinRate();
            } else{
                addedScore += map.getMatchWinRate() / bannedMapModifier;
            }
        }
        return addedScore;
    }

    //Method should be updated later, If Match is from team1's game then team1 is always team1 in that game
    private boolean isMatchUp(Match game, String team1, String team2) {
        return (game.getTeam1().equals(team1) && game.getTeam2().equals(team2)) ||
               (game.getTeam1().equals(team2) && game.getTeam2().equals(team1));
    }

    private boolean isWinner(Match game, String teamName) {
        if(game.getTeam1().equals(teamName)) {
            return Integer.parseInt(game.getScore1()) > Integer.parseInt(game.getScore2());
        } else if(game.getTeam2().equals(teamName)) {
            return Integer.parseInt(game.getScore2()) > Integer.parseInt(game.getScore1());
        }
        return false;
    }

    private final double matchUpWeight = 1.6; // Weight for match win rate

    private PredictionResponse compareTeams(TeamProfile t1, TeamProfile t2){
        PredictionResponse prediction = new PredictionResponse();

        int score1 = 0;
        int score2 = 0;

        List<Map> t1Stats = t1.getTeamMaps();
        List<Map> t2Stats = t2.getTeamMaps();

        String t1Ban = t1.probableBan();
        String t2Ban = t2.probableBan();

        score1 += tallyWinRate(t1Stats, t1Ban, t2Ban);
        score2 += tallyWinRate(t2Stats, t1Ban, t2Ban);

        int totalMatchups = 0;
        int t1MatchupWins = 0;
        for(Match game : t1.getMatches()){
            if(isMatchUp(game, t1.getTeamName(), t2.getTeamName())) {
                totalMatchups++;
               if(isWinner(game, t1.getTeamName())) t1MatchupWins++;
            }
        }

        double t1MatchupWinRate = totalMatchups > 0 ? (((double) t1MatchupWins / totalMatchups)) * 100 : 0.0;
        double t2MatchupWinRate = totalMatchups > 0 ? (100.0 - t1MatchupWinRate) : 0.0;

        score1 += t1MatchupWinRate * matchUpWeight;
        score2 += t2MatchupWinRate * matchUpWeight;

        int t1probability = (int) (((double) score1 / (score1 + score2)) * 100);
        if(score1 > score2) {
            prediction.setWinner(t1.getTeamName());
            prediction.setProbability(t1probability);
            prediction.setMatchUpWinRate(t1MatchupWinRate);
            prediction.setScore(score1);
            prediction.setTotalScore(score1 + score2);
        } else {
            prediction.setWinner(t2.getTeamName());
            prediction.setProbability(100 - t1probability);
            prediction.setMatchUpWinRate(t2MatchupWinRate);
            prediction.setScore(score2);
            prediction.setTotalScore(score1 + score2);
        }

        return prediction;
    }

    @PostMapping("/predict")
    public ResponseEntity<?> makePrediction(@RequestBody PredictionRequest request) {
        TeamProfile Team1 = new TeamProfile();
        TeamProfile Team2 = new TeamProfile();

        try{
            Team1.setTeamName(request.getTeam1());
            Team2.setTeamName(request.getTeam2());
            Team1.setMatches(dataService.lookupMatches(Team1.getTeamName()));
            Team2.setMatches(dataService.lookupMatches(Team2.getTeamName()));
        }
        catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch team data");
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Invalid request, could not parse team names");
        }
        if(Team1.getMatches() == null || Team2.getMatches() == null) {
            return ResponseEntity.status(404).body("One or both teams not found");
        }

        PredictionResponse prediction = compareTeams(Team1, Team2);
        return ResponseEntity.ok(prediction);
    }

    @GetMapping("/teamProfileTest")
    public ResponseEntity<TeamProfile> getTeamProfileTest() {
        TeamProfile teamProfile = new TeamProfile();
        teamProfile.setTeamName("NRG");
        try {
            //Team t = dataService.updateMatches(teamProfile.getTeamName());
            teamProfile.setMatches(dataService.lookupMatches(teamProfile.getTeamName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(teamProfile);
    }


    @GetMapping("/teamProfile/{teamName}")
    public TeamProfile getTeamProfile(@PathVariable String teamName) {
        TeamProfile teamProfile = new TeamProfile();
        teamProfile.setTeamName(teamName);
        try {
            //Team t = dataService.updateMatches(teamProfile.getTeamName());
            teamProfile.setMatches(dataService.lookupMatches(teamName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamProfile;
    }

    @PostMapping("/updateDB")
    public ResponseEntity<Void> updateAllTeams(){

        if(dataService.updateAllMatches()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }


}
