package com.valmatchpredictor.controller;

import com.valmatchpredictor.model.*;
import com.valmatchpredictor.service.DataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
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

    @GetMapping("/teams")
    public List<Team> showTeam(String teamName){
        try {
            List<Team> allTeams = dataService.getRankingResponse().getTeams();
            List<Team> Tier1Teams = new ArrayList<>();
            for (Team team : allTeams) {
                if(isTier1(team.getName())) {
                    Tier1Teams.add(team);
                }
            }
            System.out.println("Teams fetched: " + Tier1Teams.size());
            return Tier1Teams;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // or handle error properly
        }
    }

    @GetMapping("/matchUps")
    public List<Match> showMatchUps(String teamName1, String teamName2){
        try{
            List<Match> matchUps = dataService.fetchMatchUps("G2 Esports","Sentinels");
            return matchUps;
        } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList(); // or handle error properly
        }
    }



    @GetMapping("/teamMatches")
    public List<Match> showTeamMatches(String teamName){
        try{
            return dataService.fetchTeamMatches("G2");
        }
        catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // or handle error properly
        }
    }


    @PostMapping("/predict")
    public PredictionResponse makePrediction(@RequestBody PredictionRequest request) {
        TeamProfile Team1 = new TeamProfile();
        Team1.setTeamName(request.getTeam1());
        try{
            Team1.setMatches(dataService.fetchTeamMatches(Team1.getTeamName()));
        }
        catch (IOException e) {
            e.printStackTrace();
            return null; // or handle error properly
        }

        TeamProfile Team2 = new TeamProfile();
        Team2.setTeamName(request.getTeam2());
        try{
            Team2.setMatches(dataService.fetchTeamMatches(Team2.getTeamName()));
        }
        catch (IOException e) {
            e.printStackTrace();
            return null; // or handle error properly
        }


        PredictionResponse predictionResponse = new PredictionResponse();
        return null;
    }

    @GetMapping("/teamProfileTest")
    public TeamProfile getTeamProfileTest() {
        TeamProfile teamProfile = new TeamProfile();
        teamProfile.setTeamName("G2 Esports");
        try {
            teamProfile.setMatches(dataService.fetchTeamMatches(teamProfile.getTeamName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return teamProfile;
    }




}
