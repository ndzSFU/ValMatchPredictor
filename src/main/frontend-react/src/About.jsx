import NavBar from "./components/NavBar/NavBar";
import "./About.css";
import {ALL_TEAMS, NA_teams, CN_teams, EMEA_teams, APAC_teams} from "./Predict";
import TeamList from "./components/TeamList";

function About() {
    return (
        <div>
            <NavBar />
            <div className="docs-container">
                <h1>API Overview</h1>

                <p className="description">
                    This API provides structured access to Tier Pro Valorant team data, mainly centered around
                    match/map data for each team.
                </p>

                <section className="docs-section">
                    <h2>All Team Data Request</h2>

                    <div className="request-box">
                        <div className="method">GET</div>
                        <code>{"http://localhost:8081/teamProfile/{teamName}"}</code>
                    </div>

                    <p className="description">
                        See footer for valid spelling of each team name.
                    </p>
                </section>

                <section className="docs-section">
                    <h2>Response</h2>
                    <p className="description">
                        http://localhost:8081/teamProfile/NRG
                    </p>

                    <pre className="code-block">
          <code>{`{
  "teamName" : "NRG",
  "teamLogo" : "https://owcdn.net/img/6610f026c1a9e.png",
  "matches" : [ {
    "team1" : "Sentinels",
    "team2" : "NRG",
    "score1" : "0",
    "score2" : "1",
    "tournament_name" : "RBHG 2025",
    "matchDate" : null,
    "maps" : [ {
      "mapName" : "Pearl",
      "team1" : "NRG",
      "team2" : "Sentinels",
      "score1" : "13",
      "score2" : "11",
      "winner" : "NRG"
    } ],
    "match_page" : "https://www.vlr.gg/585450/nrg-vs-sentinels-red-bull-home-ground-2025-ubsf"
    },
    ... // The next 50 most recently played matches
   ],
    
    "teamMaps" : [ {
        "name" : "Haven",
        "amountPlayed" : 36,
        "roundWinRate" : 55,
        "roundsWon" : 410,
        "roundsPlayed" : 737,
        "mathcesWon" : 23,
        "mathcesPlayed" : 36,
        "matchWinRate" : 63,
        "pickingWeight" : 124740
      }, 
       ... // Other maps in the current competitive rotation
       ]
    }`}
          </code>
        </pre>
                </section>

                <p className="description">
                    This API provides structured access to Tier Pro Valorant team data, mainly centered around
                    match/map data for each team.
                </p>

                <section className="docs-section">
                    <h2>All Team Data Request</h2>

                    <div className="request-box">
                        <div className="method">GET</div>
                        <code>{"http://localhost:8081/teamProfile/{teamName}"}</code>
                    </div>

                    <p className="description">
                        See footer for valid spelling of each team name.
                    </p>
                </section>

                <section className="docs-section">
                    <h2>Response</h2>
                    <p className="description">
                        http://localhost:8081/teamProfile/NRG
                    </p>

                    <pre className="code-block">
          <code>{`{
  "teamName" : "NRG",
  "teamLogo" : "https://owcdn.net/img/6610f026c1a9e.png",
  "matches" : [ {
    "team1" : "Sentinels",
    "team2" : "NRG",
    "score1" : "0",
    "score2" : "1",
    "tournament_name" : "RBHG 2025",
    "matchDate" : null,
    "maps" : [ {
      "mapName" : "Pearl",
      "team1" : "NRG",
      "team2" : "Sentinels",
      "score1" : "13",
      "score2" : "11",
      "winner" : "NRG"
    } ],
    "match_page" : "https://www.vlr.gg/585450/nrg-vs-sentinels-red-bull-home-ground-2025-ubsf"
    },
    ... // The next 50 most recently played matches
   ],
    
    "teamMaps" : [ {
        "name" : "Haven",
        "amountPlayed" : 36,
        "roundWinRate" : 55,
        "roundsWon" : 410,
        "roundsPlayed" : 737,
        "mathcesWon" : 23,
        "mathcesPlayed" : 36,
        "matchWinRate" : 63,
        "pickingWeight" : 124740
      }, 
       ... // Other maps in the current competitive rotation
       ]
    }`}
          </code>
        </pre>
                </section>

                <p className="footer-note">
                    <div className={"team-lists-container"}>
                        <TeamList Title={"Americas Teams:"} Team_List={NA_teams} />

                        <TeamList Title={"EMEA Teams:"} Team_List={EMEA_teams} />

                        <TeamList Title={"APAC Teams:"} Team_List={APAC_teams} />

                        <TeamList Title={"Chinese Teams:"} Team_List={CN_teams} />
                    </div>

                </p>
            </div>
        </div>

    );
}

export default About;