package com.valmatchpredictor.controller;

import com.valmatchpredictor.model.*;
import com.valmatchpredictor.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/")
public class PredictorController {
    @Autowired
    private DataService dataService;

    int bannedMapModifier = 2;

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

    final double matchUpWeight = 1.6; // Weight for match win rate

    private PickBanResults predictPickBans(TeamProfile t1, TeamProfile t2) {
        PickBanResults pickBanResults = new PickBanResults();
        pickBanResults.simulatePickBans(t1, t2, 3);

        return pickBanResults;
    }

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

        prediction.setPreviousMatchUps(totalMatchups);

        int t1probability = (int) (((double) score1 / (score1 + score2)) * 100);
        if(score1 > score2) {
            prediction.setWinner(t1.getTeamName());
            prediction.setLoser(t2.getTeamName());
            prediction.setProbability(t1probability);
            prediction.setWinnerWinRateAgainstLoser(t1MatchupWinRate);
            prediction.setT1IsWinner(true);
        } else {
            prediction.setWinner(t2.getTeamName());
            prediction.setLoser(t1.getTeamName());
            prediction.setProbability(100 - t1probability);
            prediction.setWinnerWinRateAgainstLoser(t2MatchupWinRate);
            prediction.setT1IsWinner(false);
        }

        prediction.setPickBanResults(predictPickBans(t1, t2));

        return prediction;
    }

    @PostMapping("/predict")
    public ResponseEntity<?> makePrediction(@RequestBody PredictionRequest request) {
        TeamProfile Team1 = new TeamProfile();
        TeamProfile Team2 = new TeamProfile();

        try{
            Team1.setTeamName(request.getTeam1());
            Team2.setTeamName(request.getTeam2());
            Team1.setMatchesAndUpdateMapsAndPick(dataService.lookupMatches(Team1.getTeamName()));
            Team2.setMatchesAndUpdateMapsAndPick(dataService.lookupMatches(Team2.getTeamName()));

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to fetch team data");
        }
        if(Team1.getMatches() == null || Team2.getMatches() == null) {
            return ResponseEntity.status(404).body("One or both teams not found");
        }

        PredictionResponse prediction = compareTeams(Team1, Team2);
        try{
            prediction.setT1LogoURL(dataService.fetchTeamLogoURL(Team1.getTeamName()));
            prediction.setT2LogoURL(dataService.fetchTeamLogoURL(Team2.getTeamName()));
        }catch(Exception e){
            //Do nothing
            //Don't need the logo yet
        }

        return ResponseEntity.ok(prediction);
    }

    @GetMapping("/teamProfile/{teamName}")
    public TeamProfile getTeamProfile(@PathVariable String teamName) {
        TeamProfile teamProfile = new TeamProfile();
        teamProfile.setTeamName(teamName);
        teamProfile.setTeamLogo(dataService.fetchTeamLogoURL(teamName));
        try {
            //Team t = dataService.updateMatches(teamProfile.getTeamName());
            teamProfile.setMatchesAndUpdateMapsAndPick(dataService.lookupMatches(teamName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        teamProfile.sortMapsByPickingWeight();
        return teamProfile;
    }

    @GetMapping("/matches/{teamName}")
    public ResponseEntity<List<Match>> getMatches(@PathVariable String teamName) {
        List<Match> matches = dataService.lookupMatches(teamName);

        if (matches.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(matches);
    }

    @GetMapping("/maps/{teamName}")
    public ResponseEntity<List<Map>> getTeamMaps(@PathVariable String teamName) {
        TeamProfile teamProfile = getTeamProfile(teamName);
        if (teamProfile.getTeamMaps().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teamProfile.getTeamMaps());
    }

    @PostMapping("/updateDB")
    public ResponseEntity<Void> updateAllTeams(){

        if(dataService.updateAllMatches()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }


}
