package com.valmatchpredictor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamProfile {
    private String teamName;
    private String teamLogo;
    private List<Match> matches;
    private List<Map> teamMaps;

    public String probableBan(){
        Map LeastPlayedMap = teamMaps.getFirst();
        for(Map map : teamMaps) {
            if(map.getRoundsPlayed() < LeastPlayedMap.getRoundsPlayed()) {
                LeastPlayedMap = map;
            }
        }
        return LeastPlayedMap.getName();
    }

    public void sortMapsByPickingWeight(){
        Collections.sort(teamMaps);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public List<Map> getTeamMaps() {
        return teamMaps;
    }

    private void addToMapTally(MatchMap matchMap) {
        for(Map map : teamMaps) {
            if (matchMap.getMapName().equals(map.getName())) {
                int score1 = Integer.parseInt(matchMap.getScore1());
                int score2 = Integer.parseInt(matchMap.getScore2());
                map.played();
                map.addRoundsPlayed(score1 + score2);
                map.addMatch();
                int roundsWon = 0;
                int roundsLost = 0;
                if (matchMap.getTeam1().equals(teamName)) {
                    roundsWon = score1;
                    roundsLost = score2;
                }else if (matchMap.getTeam2().equals(teamName)) {
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

    public void setMatchesAndUpdateMaps(List<Match> matches) {
        this.matches = matches;

        for(Match match : matches) {
            for(MatchMap matchMap : match.getMaps()) {
                addToMapTally(matchMap);
            }
        }

        for(Map map : teamMaps) {
            map.calculateWinRate();
            map.calculatePickingWeight();
        }
    }

    public TeamProfile() {
        teamMaps = new ArrayList<>();
        teamMaps.add(new Map("Ascent"));
        teamMaps.add(new Map("Haven"));
        teamMaps.add(new Map("Bind"));
        teamMaps.add(new Map("Lotus"));
        teamMaps.add(new Map("Abyss"));
        teamMaps.add(new Map("Corrode"));
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
