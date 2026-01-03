package com.valmatchpredictor.model;

public class Map  implements Comparable<Map> {
    private String name;
    private int amountPlayed;
    private int roundWinRate;
    private int roundsWon;
    private int roundsPlayed;
    private int matchesWon;
    private int matchesPlayed;
    private int matchWinRate;

    private int pickingWeight;

    public void calculatePickingWeight(){
        this.pickingWeight = (roundWinRate) * (matchWinRate) * (amountPlayed);
    }

    public int getPickingWeight() {
        return pickingWeight;
    }

    @Override
    public int compareTo(Map other){
        return Integer.compare(other.getPickingWeight(), this.getPickingWeight());
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void addWonMatch() {
        this.matchesWon++;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void addMatch() {
        this.matchesPlayed++;
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
            this.roundWinRate = (int) (((double) roundsWon / (double) roundsPlayed) * 100.0);
        }

        if (matchesPlayed == 0) {
            this.matchWinRate = 0;
        } else {
            this.matchWinRate = (int) ((double) matchesWon / matchesPlayed * 100);
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
