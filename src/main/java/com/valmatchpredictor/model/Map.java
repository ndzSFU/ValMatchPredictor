package com.valmatchpredictor.model;

public class Map {
    private String name;
    private int amountPlayed;
    private int winRate;
    private int roundsWon;
    private int roundsPlayed;

    public Map(String name) {
        this.name = name;
        amountPlayed = 0;
        winRate = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountPlayed() {
        return amountPlayed;
    }

    public void played() {
        amountPlayed++;
    }

    public void calculateWinRate() {
        if (roundsPlayed == 0) {
            this.winRate = 0;
        } else {
            this.winRate = (int) ((double) roundsWon / roundsPlayed * 100);
        }
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public void addRoundsWon(int roundsWon) {
        this.roundsWon += roundsWon;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public void addRoundsPlayed(int roundsPlayed) {
        this.roundsPlayed += roundsPlayed;
    }

    public int getWinRate() {
        return winRate;
    }


}
