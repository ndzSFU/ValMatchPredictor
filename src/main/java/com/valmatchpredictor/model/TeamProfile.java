package com.valmatchpredictor.model;

import java.util.ArrayList;
import java.util.List;

public class TeamProfile {
    private String teamName;
    private String teamLogo;
    private List<Match> matches;
    private List<Map> teamMaps;

    public List<Match> getMatches() {
        return matches;
    }

    public List<Map> getTeamMaps() {
        return teamMaps;
    }

    private void addToMapTally(PlayedMap playedMap) {
        for(Map map : teamMaps) {
            if (playedMap.getMapName().equals(map.getName())) {
                int score1 = Integer.parseInt(playedMap.getScore1());
                int score2 = Integer.parseInt(playedMap.getScore2());
                map.played();
                map.addRoundsPlayed(score1 + score2);
                map.addMatch();
                int roundsWon = 0;
                int roundsLost = 0;
                if (playedMap.getTeam1().equals(teamName)) {
                    roundsWon = score1;
                    roundsLost = score2;
                }else if (playedMap.getTeam2().equals(teamName)) {
                    roundsWon = score2;
                    roundsLost = score1;
                }
                map.addRoundsWon(roundsWon);
                if(roundsWon > roundsLost) {
                    map.addWonMatch();
                }
            }
        }
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;

        for(Match match : matches) {
            for(PlayedMap matchMap : match.getMaps()) {
                addToMapTally(matchMap);
            }
        }
        for(Map map : teamMaps) {
            map.calculateWinRate();
        }
    }

    public TeamProfile() {
        teamMaps = new ArrayList<>();
        teamMaps.add(new Map("Ascent"));
        teamMaps.add(new Map("Haven"));
        teamMaps.add(new Map("Icebox"));
        teamMaps.add(new Map("Lotus"));
        teamMaps.add(new Map("Pearl"));
        teamMaps.add(new Map("Split"));
        teamMaps.add(new Map("Sunset"));
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public void setTeamLogo(String teamLogo) {
        this.teamLogo = teamLogo;
    }

}
