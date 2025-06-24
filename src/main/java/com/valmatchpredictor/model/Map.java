package com.valmatchpredictor.model;

public class Map {
    private String name;
    private int amountPlayed;
    private int roundWinRate;
    private int roundsWon;
    private int roundsPlayed;
    private int mathcesWon;
    private int mathcesPlayed;
    private int matchWinRate;

    public int getMathcesWon() {
        return mathcesWon;
    }

    public void addWonMatch() {
        this.mathcesWon++;
    }

    public int getMathcesPlayed() {
        return mathcesPlayed;
    }

    public void addMatch() {
        this.mathcesPlayed++;
    }

    public int getMatchWinRate() {
        return matchWinRate;
    }

    public Map(String name) {
        this.name = name;
        amountPlayed = 0;
        roundWinRate = 0;
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
            this.roundWinRate = 0;
        } else {
            this.roundWinRate = (int) ((double) roundsWon / roundsPlayed * 100);
        }

        if (mathcesPlayed == 0) {
            this.matchWinRate = 0;
        } else {
            this.matchWinRate = (int) ((double) mathcesWon / mathcesPlayed * 100);
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

    public int getRoundWinRate() {
        return roundWinRate;
    }


}
