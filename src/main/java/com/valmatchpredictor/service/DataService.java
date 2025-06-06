package com.valmatchpredictor.service;
import com.valmatchpredictor.model.Map;
import com.valmatchpredictor.model.Match;
import com.valmatchpredictor.model.MatchesResponse;
import com.valmatchpredictor.model.RankingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private final String TEAMS_API_URL = "https://vlrggapi.vercel.app/rankings?region=na";

    public RankingResponse getRankingResponse(){
        RestTemplate restTemplate = new RestTemplate();
        RankingResponse ranking_response = restTemplate.getForObject(TEAMS_API_URL, RankingResponse.class);
        return ranking_response;
    }

    private final String MATCHES_API_URL = "https://vlrggapi.vercel.app/match?q=results";
    public List<Match> fetchMatchUps(String team1, String team2){

            RestTemplate restTemplate = new RestTemplate();
            MatchesResponse matchesResponse = restTemplate.getForObject(MATCHES_API_URL, MatchesResponse.class);
            List<Match> allMatches = matchesResponse.getData().getSegments();
            List<Match> matchUps = new ArrayList<>();

            for(Match match : allMatches){
                if(match.getTeam1().equals(team1) || match.getTeam2().equals(team1)
                || match.getTeam1().equals(team2) || match.getTeam2().equals(team2)){
                    matchUps.add(match);
                }
            }
            return allMatches;
    }

    String nrg_id = "1034";
    String g2_id = "11058";

    public String getTeamID (String teamName) {
        switch (teamName) {
            case "NRG":
                return nrg_id;
            case "G2":
                return g2_id;
            // Add more cases for other teams as needed
            default:
                return null; // or throw an exception if team not found
        }
    }

    public String getTeamNameUrlFormat(String teamName) {
        switch (teamName) {
            case "NRG":
                return "nrg";
            case "G2":
                return "g2-esports";
            // Add more cases for other teams as needed
            default:
                return null; // or throw an exception if team not found
        }
    }

    public List<Match> fetchTeamMatches(String teamName) throws IOException {
        String url = "https://www.vlr.gg/team/matches/" + getTeamID(teamName) + "/" + getTeamNameUrlFormat(teamName) + "/";
        Document doc = Jsoup.connect(url).get();
        List<Match> matches = new ArrayList<>();

        Elements matchLinks = doc.select("a.wf-card.m-item");
        for (Element matchElem : matchLinks) {
            String matchUrl = "https://www.vlr.gg" + matchElem.attr("href");
            String event = matchElem.selectFirst(".m-item-event .text-of").text();
            String team1 = matchElem.select(".m-item-team .m-item-team-name").first().text();
            String team2 = matchElem.select(".m-item-team.mod-right .m-item-team-name").text();
            Elements scores = matchElem.select(".m-item-result span");
            String score1 = scores.size() > 0 ? scores.get(0).text() : "";
            String score2 = scores.size() > 1 ? scores.get(1).text() : "";
            String date = matchElem.select(".m-item-date div").text();
            //String time = matchElem.select(".m-item-date").ownText();

            Match match = new Match();
            match.setMatchUrl(matchUrl);
            match.setTournament_name(event);
            match.setTeam1(team1);
            match.setTeam2(team2);
            match.setScore1(score1);
            match.setScore2(score2);
            //match.setTimeUntilMatch(date + " " + time);

            List<Map> maps = new ArrayList<>();

            try {
                Document matchDoc = Jsoup.connect(matchUrl).get();
                Element statsContainer = matchDoc.selectFirst("div.vm-stats-container");
                if (statsContainer != null) {
                    Elements mapDivs = statsContainer.select("div.vm-stats-game");
                    for (Element mapDiv : mapDivs) {
                        Map map = new Map();
                        // Extract map name
                        Element header = mapDiv.selectFirst(".vm-stats-game-header");
                        if (header != null) {
                            Elements teams = header.select(".team");
                            if (teams.size() == 2) {
                                // Team 1 (left)
                                Element team1NameElem = teams.get(0).selectFirst(".team-name");
                                Element score1Elem = teams.get(0).selectFirst(".score");
                                // Team 2 (right)
                                Element team2NameElem = teams.get(1).selectFirst(".team-name");
                                Element score2Elem = teams.get(1).selectFirst(".score");

                                map.setTeam1(team1NameElem != null ? team1NameElem.text().trim() : null);
                                map.setScore1(score1Elem != null ? score1Elem.text().trim() : null);
                                map.setTeam2(team2NameElem != null ? team2NameElem.text().trim() : null);
                                map.setScore2(score2Elem != null ? score2Elem.text().trim() : null);

                                // Determine winner
                                try {
                                    int s1 = Integer.parseInt(map.getScore1().trim());
                                    int s2 = Integer.parseInt(map.getScore2().trim());
                                    map.setWinner(s1 > s2 ? map.getTeam1() : map.getTeam2());
                                } catch (Exception ignored) {}
                            }

                            // Map name
                            Element mapNameElem = header.selectFirst(".map > div > span");
                            if (mapNameElem != null) {
                                map.setMapName(mapNameElem.ownText().trim());
                            } else {
                                map.setMapName("Unknown Map");
                            }
                        }

                        if(map.getMapName() != null) maps.add(map);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            match.setMaps(maps);

            matches.add(match);
        }
        return matches;
    }








}
