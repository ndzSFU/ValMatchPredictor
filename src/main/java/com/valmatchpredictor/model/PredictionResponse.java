package com.valmatchpredictor.model;

public class PredictionResponse {
    private String winner;
    private String loser;
    private int probability;
    private int previousMatchUps;
    private double winnerWinRateAgainstLoser;
    private String t1LogoURL;
    private String t2LogoURL;
    private PickBanResults pickBanResults;
    private boolean t1IsWinner;


    public PredictionResponse() {}

    public PredictionResponse(String winner, int probability) {
        this.winner = winner;
        this.probability = probability;
    }

    public boolean isT1IsWinner() {
        return t1IsWinner;
    }

    public void setT1IsWinner(boolean t1IsWinner) {
        this.t1IsWinner = t1IsWinner;
    }

    public int getPreviousMatchUps() {
        return previousMatchUps;
    }

    public void setPreviousMatchUps(int previousMatchUps) {
        this.previousMatchUps = previousMatchUps;
    }

    public PickBanResults getPickBanResults() {
        return pickBanResults;
    }

    public void setPickBanResults(PickBanResults pickBanResults) {
        this.pickBanResults = pickBanResults;
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

    public void setWinnerWinRateAgainstLoser(double matchWinUpRate) {
        this.winnerWinRateAgainstLoser = matchWinUpRate;
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

    public double getWinnerWinRateAgainstLoser() {
        return winnerWinRateAgainstLoser;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
