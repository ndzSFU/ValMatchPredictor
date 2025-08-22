package com.valmatchpredictor.model;

public class PredictionResponse {
    private String winner;
    private int probability;
    private double matchUpWinRate;
    private int score;
    private int totalScore;

    public PredictionResponse() {
    }

    public PredictionResponse(String winner, int probability) {
        this.winner = winner;
        this.probability = probability;
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
