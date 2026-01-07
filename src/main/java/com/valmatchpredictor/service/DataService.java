package com.valmatchpredictor.service;

import com.valmatchpredictor.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class DataService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private MatchRepo matchRepo;


    public class DataAccessException extends RuntimeException {
        public DataAccessException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    String[] allTeams = {
            // Americas
            "NRG",               "G2 Esports",        "Sentinels",         "100 Thieves",
            "Cloud9",            "Evil Geniuses",     "LEVIATÁN",          "KRÜ Esports",
            "MIBR",              "FURIA",             "LOUD",              "ENVY",

            // EMEA
            "FNATIC",            "Natus Vincere",              "Team Heretics",     "BBL Esports",
            "Team Liquid",       "FUT Esports",       "Team Vitality",     "Gentle Mates",
            "GIANTX",            "Karmine Corp",      "ULF Esports",       "BBL PCIFIC",

            // APAC
            "DetonatioN FocusMe","DRX",               "Gen.G",             "Global Esports",
            "Paper Rex",         "Rex Regum Qeon",    "T1",                "VARREL",
            "Team Secret",       "ZETA DIVISION",     "FULL SENSE",      "Nongshim RedForce",

            // China
            "All Gamers",        "Bilibili Gaming",   "EDward Gaming",     "FunPlus Phoenix",
            "JDG Esports",       "Nova Esports",      "Titan Esports Club","Trace Esports",
            "TYLOO",             "Wolves Esports",    "Xi Lai Gaming",     "Dragon Ranger Gaming"
    };

    List<String> allTeamsAR = new ArrayList<>(Arrays.asList(allTeams));

    //NA teams
    String nrg_id = "1034";
    String g2_id = "11058";
    String sen_id = "2";
    String t100_id = "120";
    String cloud9_id = "188";
    String eg_id = "5248";
    String lev_id = "2359";
    String kru_id = "2355";
    String mibr_id = "7386";
    String furia_id = "2406";
    String loud_id = "6961";
    String envy_id = "427";


    //EU teams
    String fnc_id = "2593";
    String navi_id = "4915";
    String th_id = "1001";
    String bbl_id = "397";
    String tl_id = "474";
    String fut_id = "1184";
    String vit_id = "2059";
    String m8_id = "12694";
    String gx_id = "14419";
    String kc_id = "8877";
    String ulf_id = "18019";
    String bbl_pc_id = "16616";

    //APAC
    String dfm_id = "278";
    String drx_id = "8185";
    String geng_id = "17";
    String ge_id = "918";
    String prx_id = "624";
    String rrq_id = "878";
    String t1_id = "14";
    String tln_id = "8304";
    String ts_id = "6199";
    String zeta_id = "5448";
    String slt_id = "12446";
    String nsrf_id = "11060";
    String fs_id = "4050";
    String vl_id = "11229";

    //China
    String ag_id = "1119";
    String blg_id = "12010";
    String edg_id = "1120";
    String fpx_id = "11328";
    String jdg_id = "13576";
    String nova_id = "12064";
    String tec_id = "14137";
    String te_id = "12685";
    String tyloo_id = "731";
    String wol_id = "13790";
    String xlg_id = "13581";
    String drg_id = "11981";

    //Takes the nicely capitalized version of the name displayed on the front-end and return the team ID (for the vlr url)
    public String getTeamID(String teamName) {
        return switch (teamName) {
            case "NRG" -> nrg_id;
            case "G2 Esports" -> g2_id;
            case "Sentinels" -> sen_id;
            case "100 Thieves" -> t100_id;
            case "Cloud9" -> cloud9_id;
            case "Evil Geniuses" -> eg_id;
            case "LEVIATÁN" -> lev_id;
            case "KRÜ Esports" -> kru_id;
            case "MIBR" -> mibr_id;
            case "FURIA" -> furia_id;
            case "LOUD" -> loud_id;
            case "ENVY" -> envy_id;

            //Eu
            case "FNATIC" -> fnc_id;
            case "Natus Vincere" -> navi_id;
            case "Team Heretics" -> th_id;
            case "BBL Esports" -> bbl_id;
            case "Team Liquid" -> tl_id;
            case "FUT Esports" -> fut_id;
            case "Team Vitality" -> vit_id;
            case "Gentle Mates" -> m8_id;
            case "GIANTX" -> gx_id;
            case "Karmine Corp" -> kc_id;
            case "ULF Esports" -> ulf_id;
            case "BBL PCIFIC" -> bbl_pc_id;

            //APAC
            case "DetonatioN FocusMe" -> dfm_id;
            case "DRX" -> drx_id;
            case "Gen.G" -> geng_id;
            case "Global Esports" -> ge_id;
            case "Paper Rex" -> prx_id;
            case "Rex Regum Qeon" -> rrq_id;
            case "T1" -> t1_id;
            case "TALON" -> tln_id;
            case "Team Secret" -> ts_id;
            case "ZETA DIVISION" -> zeta_id;
            case "SLT Seongnam" -> slt_id;
            case "Nongshim RedForce" -> nsrf_id;
            case "FULL SENSE" -> fs_id;
            case "VARREL" -> vl_id;

            //China
            case "All Gamers" -> ag_id;
            case "Bilibili Gaming" -> blg_id;
            case "EDward Gaming" -> edg_id;
            case "FunPlus Phoenix" -> fpx_id;
            case "JDG Esports" -> jdg_id;
            case "Nova Esports" -> nova_id;
            case "Titan Esports Club" -> tec_id;
            case "Trace Esports" -> te_id;
            case "TYLOO" -> tyloo_id;
            case "Wolves Esports" -> wol_id;
            case "Xi Lai Gaming" -> xlg_id;
            case "Dragon Ranger Gaming" -> drg_id;

            default -> "0";
        };
    }

    //Takes the nicely capitalized version of the name displayed on the front-end and returns what their name is in the vlr url
    public String getTeamNameUrlFormat(String teamName) {
        return switch (teamName) {
            //Americas
            case "NRG" -> "nrg";
            case "G2 Esports" -> "g2-esports";
            case "Sentinels" -> "sentinels";
            case "100 Thieves" -> "100-thieves";
            case "Cloud9" -> "cloud9";
            case "Evil Geniuses" -> "evil-geniuses";
            case "LEVIATÁN" -> "leviat-n";
            case "KRÜ Esports" -> "kr-esports";
            case "MIBR" -> "mibr";
            case "FURIA" -> "furia";
            case "LOUD" -> "loud";
            case "ENVY" -> "envy";

            //EU
            case "FNATIC" -> "fnatic";
            case "Natus Vincere" -> "natus-vincere";
            case "Team Heretics" -> "team-heretics";
            case "BBL Esports" -> "bbl-esports";
            case "Team Liquid" -> "team-liquid";
            case "FUT Esports" -> "fut-esports";
            case "Team Vitality" -> "team-vitality";
            case "Gentle Mates" -> "gentle-mates";
            case "GIANTX" -> "giantx";
            case "Karmine Corp" -> "karmine-corp";
            case "ULF Esports" -> "ulf-esports";
            case "BBL PCIFIC" -> "bbl-pcific";

            //APAC
            case "DetonatioN FocusMe" -> "detonation-focusme";
            case "DRX" -> "drx";
            case "Gen.G" -> "gen-g";
            case "Global Esports" -> "global-esports";
            case "Paper Rex" -> "paper-rex";
            case "Rex Regum Qeon" -> "rex-regum-qeon";
            case "T1" -> "t1";
            case "TALON" -> "talon";
            case "Team Secret" -> "team-secret";
            case "ZETA DIVISION" -> "zeta-division";
            case "SLT Seongnam" -> "slt-seongnam";
            case "Nongshim RedForce" -> "nongshim-redforce";
            case "FULL SENSE" -> "full-sense";
            case "VARREL" -> "varrel";

            //China
            case "All Gamers" -> "all-gamers";
            case "Bilibili Gaming" -> "bilibili-gaming";
            case "EDward Gaming" -> "edward-gaming";
            case "FunPlus Phoenix" -> "funplus-phoenix";
            case "JDG Esports" -> "jdg-esports";
            case "Nova Esports" -> "nova-esports";
            case "Titan Esports Club" -> "titan-esports-club";
            case "Trace Esports" -> "trace-esports";
            case "TYLOO" -> "tyloo";
            case "Wolves Esports" -> "wolves-esports";
            case "Xi Lai Gaming" -> "xi-lai-gaming";
            case "Dragon Ranger Gaming" -> "dragon-ranger-gaming";

            default -> teamName;
        };
    }

    public boolean clearDatabase(){
        matchRepo.deleteAll();
        teamRepo.deleteAll();
        return true;
    }

    public boolean updateMatches(String teamName) throws IOException {
        List<Match> matches;
        //Scrape to hard update the games
        matches = ScrapeTeamMatches(teamName);

        Team team = teamRepo.findByTeamName(teamName).orElseGet(() -> {
                    Team newTeam = new Team();
                    newTeam.setTeamName(teamName);
                    return newTeam;
                });
        team.setMatches(matches);
        teamRepo.save(team);

        return true;
    }

    public List<Match> lookupMatches(String teamName) {
        try {
            return matchRepo.findMatches(teamName);
        } catch (Exception e) {
            throw new DataAccessException("Failed to lookup matches for team: " + teamName, e);
        }
    }

    public boolean updateAllMatches() {
        clearDatabase();
        for(String team : allTeams) {
            try{
                updateMatches(team);
            } catch(IOException e){
                e.printStackTrace();
                //return false;
            }
        }
        return true;
    }

    public Team fetchTeam(String teamName) throws IOException {
        Optional<Team> teamOpt = teamRepo.findByTeamName(teamName);
        return teamOpt.orElse(null);
    }

    public String fetchTeamLogoURL(String teamName) {
        Optional<String> logoUrlOpt = teamRepo.findLogoURLByTeamName(teamName);
        return logoUrlOpt.orElse("no url");
    }

    private void saveTeamLogo(String teamName, String logoUrl) {
        Optional<Team> possibleTeam = teamRepo.findByTeamName(teamName);
        if (possibleTeam.isPresent()) {
            Team team = possibleTeam.get();
            team.setLogoURL(logoUrl);
            teamRepo.save(team);
        }else{
            Team newTeam = new Team();
            newTeam.setTeamName(teamName);
            newTeam.setLogoURL(logoUrl);
            teamRepo.save(newTeam);
        }
    }

    public List<Match> ScrapeTeamMatches(String teamName) throws IOException {
        //Incoming teamName should be the version displayed on the front end
        String url = "https://www.vlr.gg/team/matches/" + getTeamID(teamName) + "/" + getTeamNameUrlFormat(teamName) + "/";
        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                + "(KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36").timeout(60_000).get();
        List<Match> matches = new ArrayList<>();

        Elements matchLinks = doc.select("a.wf-card.m-item");
        for (Element matchElem : matchLinks) {
            String matchUrl = "https://www.vlr.gg" + matchElem.attr("href");
            String event = matchElem.selectFirst(".m-item-event .text-of").text();
            String team1 = matchElem.select(".m-item-team .m-item-team-name").first().text();
            String team2 = matchElem.select(".m-item-team.mod-right .m-item-team-name").text();

            if(!allTeamsAR.contains(team1) && !allTeamsAR.contains(team2)){
                continue; // Skip tier 2 matches
            }

            Elements scores = matchElem.select(".m-item-result span");
            String score1 = scores.size() > 0 ? scores.get(0).text() : "";
            String score2 = scores.size() > 1 ? scores.get(1).text() : "";

            Element team1LogoImg = matchElem.selectFirst(".m-item-logo img");

            Element team2LogoImg = matchElem.selectFirst(".m-item-logo.mod-right img");

            String matchDate = matchElem.select(".m-item-date").text().trim();

            String team1LogoUrl = team1LogoImg != null
                    ? team1LogoImg.absUrl("src")
                    : "Could not find url";

            String team2LogoUrl = team2LogoImg != null
                    ? team2LogoImg.absUrl("src")
                    : "could not find url2";

            if (!team1LogoUrl.isEmpty() && allTeamsAR.contains(team1)) {
                saveTeamLogo(team1, team1LogoUrl);
            }

            if (!team2LogoUrl.isEmpty() && allTeamsAR.contains(team2)) {
                saveTeamLogo(team2, team2LogoUrl);
            }



            Match match = new Match();
            match.setMatchUrl(matchUrl);
            match.setTournament_name(event);
            match.setTeam1(team1);
            match.setTeam2(team2);
            match.setScore1(score1);
            match.setScore2(score2);
            match.setDate(matchDate);


            List<MatchMap> matchMaps = new ArrayList<>();

           //System.out.println("Scraping matches for " + teamName);
            try {
                Document matchDoc = Jsoup.connect(matchUrl).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                        + "(KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36").timeout(60_000).get();
                Element statsContainer = matchDoc.selectFirst("div.vm-stats-container");
                if (statsContainer != null) {
                    Elements mapDivs = statsContainer.select("div.vm-stats-game");
                    for (Element mapDiv : mapDivs) {
                        MatchMap matchMap = new MatchMap();
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

                                matchMap.setTeam1(team1NameElem != null ? team1NameElem.text().trim() : null);
                                matchMap.setScore1(score1Elem != null ? score1Elem.text().trim() : null);
                                matchMap.setTeam2(team2NameElem != null ? team2NameElem.text().trim() : null);
                                matchMap.setScore2(score2Elem != null ? score2Elem.text().trim() : null);

                                // Determine winner
                                try {
                                    int s1 = Integer.parseInt(matchMap.getScore1().trim());
                                    int s2 = Integer.parseInt(matchMap.getScore2().trim());
                                    matchMap.setWinner(s1 > s2 ? matchMap.getTeam1() : matchMap.getTeam2());
                                } catch (Exception ignored) {
                                }
                            }

                            // Map name
                            Element mapNameElem = header.selectFirst(".map > div > span");
                            if (mapNameElem != null) {
                                matchMap.setMapName(mapNameElem.ownText().trim());
                            } else {
                                matchMap.setMapName("Unknown Map");
                            }
                        }

                        if (matchMap.getMapName() != null) matchMaps.add(matchMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            match.setMaps(matchMaps);

            matches.add(match);
        }
        return matches;
    }
}
