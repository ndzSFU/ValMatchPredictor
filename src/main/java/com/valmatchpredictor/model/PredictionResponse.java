package com.valmatchpredictor.model;

import java.util.ArrayList;

public class PredictionResponse {
    private String winner;
    private String loser;
    private int probability;
    private double matchUpWinRate;
    private int score;
    private int totalScore;
    private String t1Ban;
    private String t2Ban;
    private String t1LogoURL;
    private String t2LogoURL;

    public PredictionResponse() {}

    public PredictionResponse(String winner, int probability) {
        this.winner = winner;
        this.probability = probability;
    }

    public String getLoser() {
        return loser;
    }

    public void setLoser(String loser) {
        this.loser = loser;
    }

    public String getT1LogoURL() {
        return t1LogoURL;
    }

    public void setT1LogoURL(String t1LogoURL) {
        this.t1LogoURL = t1LogoURL;
    }

    public String getT2LogoURL() {
        return t2LogoURL;
    }

    public void setT2LogoURL(String t2LogoURL) {
        this.t2LogoURL = t2LogoURL;
    }

    public void setT1Ban(String t1Ban) {
        this.t1Ban = t1Ban;
    }

    public void setT2Ban(String t2Ban) {
        this.t2Ban = t2Ban;
    }

    public String getT1Ban() {
        return t1Ban;
    }

    public String getT2Ban() {
        return t2Ban;
    }

    public void setMatchUpWinRate(double matchWinUpRate) {
        this.matchUpWinRate = matchWinUpRate;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getProbability() {
        return probability;
    }

    public double getMatchUpWinRate() {
        return matchUpWinRate;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
