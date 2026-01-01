package com.valmatchpredictor.model;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class PickBanResults {

    public enum Result{
        T1BAN,
        T2BAN,
        T1PICK,
        T2PICK,
        DECIDER,
    }

    public PickBanResults(){
        mapResults = new HashMap<>();
        mapOrdering = new ArrayList<>();
    }

    private ArrayList<String> mapOrdering;

    public HashMap<String, Result> mapResults;

    public HashMap<String, Result> getMapResults() {
        return mapResults;
    }

    public ArrayList<String> getMapOrdering() {
        return mapOrdering;
    }

    private int findNextBan(TeamProfile team, int currentBanIdx){
        while(currentBanIdx < 7) {
            Map mapToBan = team.getTeamMaps().get(currentBanIdx);

            if(!mapResults.containsKey(mapToBan.getName())){
                mapOrdering.add(mapToBan.getName());
                return currentBanIdx;
            }
            currentBanIdx++;
        }

        //Force a crash, should never reach here
        return -1;
    }

    private int findNextPick(TeamProfile team, int currentPickIdx){
        while(currentPickIdx >= 0) {
            Map mapPick = team.getTeamMaps().get(currentPickIdx);

            if(!mapResults.containsKey(mapPick.getName())){
                mapOrdering.add(mapPick.getName());
                return currentPickIdx;
            }
            currentPickIdx--;
        }

        return -1;
    }

    private int findDecider(TeamProfile team){
        for(int i = 0; i < 7; i++) {
            Map mapPick = team.getTeamMaps().get(i);

            if(!mapResults.containsKey(mapPick.getName())){
                mapOrdering.add(mapPick.getName());
                return i;
            }
        }

        return -1;
    }

    private void simulateBO3(TeamProfile team1, TeamProfile team2){
        team1.sortMapsByPickingWeight();
        team2.sortMapsByPickingWeight();

        int t1MapBanIdx = 0;
        int t2MapBanIdx = 0;
        int t1PickIdx = 6;
        int t2PickIdx = 6;
        List<Map> t1Maps = team1.getTeamMaps();
        List<Map> t2Maps = team2.getTeamMaps();

        assert(t1Maps.size() >= 7);
        assert(t2Maps.size() >= 7);

        //First Sets of Bans
        t1MapBanIdx = findNextBan(team1, t1MapBanIdx);
        mapResults.put(t1Maps.get(t1MapBanIdx).getName(), Result.T1BAN);

        t2MapBanIdx = findNextBan(team2, t2MapBanIdx);
        mapResults.put(t2Maps.get(t2MapBanIdx).getName(), Result.T2BAN);

        //Both teams pick
        t1PickIdx = findNextPick(team1, t1PickIdx);
        mapResults.put(team1.getTeamMaps().get(t1PickIdx).getName(), Result.T1PICK);

        t2PickIdx = findNextPick(team2, t2PickIdx);
        mapResults.put(team2.getTeamMaps().get(t2PickIdx).getName(), Result.T2PICK);


        //Seconds bans
        t1MapBanIdx = findNextBan(team1, t1MapBanIdx);
        mapResults.put(t1Maps.get(t1MapBanIdx).getName(), Result.T1BAN);

        t2MapBanIdx = findNextBan(team2, t2MapBanIdx);
        mapResults.put(t2Maps.get(t2MapBanIdx).getName(), Result.T2BAN);

        String deciderMap = team1.getTeamMaps().get(findDecider(team1)).getName();
        mapResults.put(deciderMap, Result.DECIDER);
    }

    public void simulatePickBans(TeamProfile team1, TeamProfile team2, int bestOf){
        Random rand = new Random();
        int coinFlip = rand.nextInt(2) + 1;


        if(bestOf == 3){
            if(coinFlip == 1){

                simulateBO3(team1, team2);

            }else if(coinFlip == 2){
                //Flip the order so team 2 is the one banning/picking first
                simulateBO3(team2, team1);
            }
        }


    }
}

