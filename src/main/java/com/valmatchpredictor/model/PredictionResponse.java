package com.valmatchpredictor.model;

public class PredictionResponse {
    private String winner;
    private int probability;

    public PredictionResponse() {
    }

    public PredictionResponse(String winner, int probability) {
        this.winner = winner;
        this.probability = probability;
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

    public void setProbability(int probability) {
        this.probability = probability;
    }
}
