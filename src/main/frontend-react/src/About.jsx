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
                    This API provides structured access to Tier 1 Pro Valorant team data, mainly centered around
                    match/map data for each team.
                </p>

                <section className="docs-section">
                    <h2>All Team Data Request</h2>

                    <div className="request-box">
                        <div className="method">GET</div>
                        <code>{"http://localhost:8081/teamProfile/{teamName}"}</code>
                    </div>

                    <p className="description">
                        Fetch all available data for the specified team.
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
    "team1" : "NRG",
    "team2" : "G2 Esports",
    "score1" : "0",
    "score2" : "3",
    "tournament_name" : "RBHG 2025",
    "date" : "2025/11/16 12:35 pm",
    "maps" : [ {
      "mapName" : "Corrode",
      "team1" : "NRG",
      "team2" : "G2 Esports",
      "score1" : "10",
      "score2" : "13",
      "winner" : "G2 Esports"
    }, {
      "mapName" : "Split",
      "team1" : "NRG",
      "team2" : "G2 Esports",
      "score1" : "4",
      "score2" : "13",
      "winner" : "G2 Esports"
    }, {
      "mapName" : "Pearl",
      "team1" : "NRG",
      "team2" : "G2 Esports",
      "score1" : "7",
      "score2" : "13",
      "winner" : "G2 Esports"
    } ],
    "match_page" : "https://www.vlr.gg/585453/nrg-vs-g2-esports-red-bull-home-ground-2025-gf"
  },
    ... // The next 50 most recently played matches
   ],
    
    "teamMaps" : [ {
        "name" : "Haven",
        "amountPlayed" : 36,
        "roundWinRate" : 55,
        "roundsWon" : 410,
        "roundsPlayed" : 737,
        "matchesWon" : 23,
        "matchesPlayed" : 36,
        "matchWinRate" : 6
      }, 
       ... // Other maps in the current competitive rotation
       ]
}`}
          </code>
        </pre>
                </section>

                <section className="docs-section">
                    <h2>Map Data Request</h2>

                    <div className="request-box">
                        <div className="method">GET</div>
                        <code>{"http://localhost:8081/maps/{teamName}"}</code>
                    </div>

                    <p className="description">
                        Fetch the 50 most recently played matches for the specified team.
                        See footer for valid spelling of each team name.
                    </p>
                </section>

                <section className="docs-section">
                    <h2>Response</h2>
                    <p className="description">
                        http://localhost:8081/maps/Cloud9
                    </p>

                    <pre className="code-block">
          <code>{`[ {
  "name" : "Lotus",
  "amountPlayed" : 40,
  "roundWinRate" : 51,
  "roundsWon" : 441,
  "roundsPlayed" : 864,
  "matchesWon" : 22,
  "matchesPlayed" : 40,
  "matchWinRate" : 55
}, {
  "name" : "Sunset",
  "amountPlayed" : 13,
  "roundWinRate" : 60,
  "roundsWon" : 162,
  "roundsPlayed" : 267,
  "matchesWon" : 12,
  "matchesPlayed" : 13,
  "matchWinRate" : 92
}, {
  "name" : "Bind",
  "amountPlayed" : 19,
  "roundWinRate" : 53,
  "roundsWon" : 217,
  "roundsPlayed" : 403,
  "matchesWon" : 10,
  "matchesPlayed" : 19,
  "matchWinRate" : 52
}, {
  "name" : "Haven",
  "amountPlayed" : 32,
  "roundWinRate" : 46,
  "roundsWon" : 315,
  "roundsPlayed" : 676,
  "matchesWon" : 11,
  "matchesPlayed" : 32,
  "matchWinRate" : 34
}, {
  "name" : "Abyss",
  "amountPlayed" : 14,
  "roundWinRate" : 49,
  "roundsWon" : 154,
  "roundsPlayed" : 314,
  "matchesWon" : 5,
  "matchesPlayed" : 14,
  "matchWinRate" : 35
}, {
  "name" : "Ascent",
  "amountPlayed" : 18,
  "roundWinRate" : 43,
  "roundsWon" : 157,
  "roundsPlayed" : 357,
  "matchesWon" : 4,
  "matchesPlayed" : 18,
  "matchWinRate" : 22
}, {
  "name" : "Corrode",
  "amountPlayed" : 6,
  "roundWinRate" : 40,
  "roundsWon" : 50,
  "roundsPlayed" : 122,
  "matchesWon" : 2,
  "matchesPlayed" : 6,
  "matchWinRate" : 33
} ]`}
          </code>
        </pre>
                </section>


                <h2>Match Data Request</h2>

                <div className="request-box">
                    <div className="method">GET</div>
                    <code>{"http://localhost:8081/matches/{teamName}"}</code>
                </div>

                <p className="description">
                    Fetch the 50 most recently played matches for the specified team.
                    See footer for valid spelling of each team name.
                </p>

                <section className="docs-section">
                    <h2>Response</h2>
                    <p className="description">
                        http://localhost:8081/matches/FNATIC
                    </p>

                    <pre className="code-block">
          <code>{`[ {
    "team1" : "NRG",
    "team2" : "FNATIC",
    "score1" : "3",
    "score2" : "2",
    "tournament_name" : "Champions 2025",
    "date" : "2025/10/05 4:00 am",
    "maps" : [ {
      "mapName" : "Corrode",
      "team1" : "NRG",
      "team2" : "FNATIC",
      "score1" : "13",
      "score2" : "3",
      "winner" : "NRG"
    }, {
      "mapName" : "Lotus",
      "team1" : "NRG",
      "team2" : "FNATIC",
      "score1" : "13",
      "score2" : "6",
      "winner" : "NRG"
    }, {
      "mapName" : "Abyss",
      "team1" : "NRG",
      "team2" : "FNATIC",
      "score1" : "13",
      "score2" : "15",
      "winner" : "FNATIC"
    }, {
      "mapName" : "Ascent",
      "team1" : "NRG",
      "team2" : "FNATIC",
      "score1" : "8",
      "score2" : "13",
      "winner" : "FNATIC"
    }, {
      "mapName" : "Sunset",
      "team1" : "NRG",
      "team2" : "FNATIC",
      "score1" : "13",
      "score2" : "5",
      "winner" : "NRG"
    } ],
    "match_page" : "https://www.vlr.gg/542272/nrg-vs-fnatic-valorant-champions-2025-gf"
  }, {
    "team1" : "NRG",
    "team2" : "FNATIC",
    "score1" : "2",
    "score2" : "0",
    "tournament_name" : "Champions 2025",
    "date" : "2025/10/03 4:00 am",
    "maps" : [ {
      "mapName" : "Ascent",
      "team1" : "FNATIC",
      "team2" : "NRG",
      "score1" : "12",
      "score2" : "14",
      "winner" : "NRG"
    }, {
      "mapName" : "Abyss",
      "team1" : "FNATIC",
      "team2" : "NRG",
      "score1" : "5",
      "score2" : "13",
      "winner" : "NRG"
    } ],
    "match_page" : "https://www.vlr.gg/542270/fnatic-vs-nrg-valorant-champions-2025-ubf"
  },
...// and so on
]
`}
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