package com.valmatchpredictor.model;

public class PredictionRequest {
    private String team1;
    private String team2;
    private String bestOf;

    public PredictionRequest() {
    }

    public String getBestOf() {
        return bestOf;
    }

    public void setBestOf(String bestOf) {
        this.bestOf = bestOf;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }
}
