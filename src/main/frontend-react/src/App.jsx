import React, { useState } from 'react';
import Dropdown from './components/Dropdown/Dropdown';
import DropdownItem from "./components/DropdownItem/DropdownItem";
import './App.css';
import SetTeamListButton from "./components/SetTeamButton/SetTeamListButton";

let NA_teams = [
    "NRG",               "G2 Esports",        "Sentinels",         "100 Thieves",
    "Cloud9",            "Evil Geniuses",     "LEVIATÁN",          "KRÜ Esports",
    "MIBR",              "Furia",             "LOUD",              "ENVY"
]

let EMEA_teams = [
    "FNATIC",            "NAVI",              "Team Heretics",     "BBL Esports",
    "Team Liquid",       "FUT Esports",       "Team Vitality",     "Gentle Mates",
    "GIANTX",            "Karmine Corp",      "ULF Esports",       "BBL PCIFIC"
]

let APAC_teams = [
    "DetonatioN FocusMe","DRX",               "Gen.G",             "Global Esports",
    "Paper Rex",         "Rex Regum Qeon",    "T1",                "TALON",
    "Team Secret",       "ZETA DIVISION",     "SLT Seongnam",      "Nongshim RedForce",
]

let CN_teams = [
    "All Gamers",        "Bilibili Gaming",   "EDward Gaming",     "FunPlus Phoenix",
    "JDG Esports",       "Nova Esports",      "Titan Esports Club","Trace Esports",
    "TYLOO",             "Wolves Esports",    "Xi Lai Gaming",     "Dragon Ranger Gaming"
]

function App() {
    const [team1, setTeam1] = useState('');
    const [team2, setTeam2] = useState('');
    const [response, setResponse] = useState('');
    const [displayRegion1, setDisplayRegion1] = useState(NA_teams);
    const [displayRegion2, setDisplayRegion2] = useState(NA_teams);



    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await fetch('http://localhost:8081/predict', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ team1, team2 }),
            });
            const data = await res.json();
            setResponse(JSON.stringify(data));
        } catch (error) {
            setResponse('Error: ' + error.message);
        }
    };

    const CreateButtonGroup = ({setDisplayRegion}) => {
        return (
            <div>
                <SetTeamListButton title={"NA"} setTeamList={setDisplayRegion} teamList={NA_teams}></SetTeamListButton>

                <SetTeamListButton title={"EMEA"} setTeamList={setDisplayRegion} teamList={EMEA_teams}></SetTeamListButton>

                <SetTeamListButton title={"APAC"} setTeamList={setDisplayRegion} teamList={APAC_teams}></SetTeamListButton>

                <SetTeamListButton title={"CHINA"} setTeamList={setDisplayRegion} teamList={CN_teams}></SetTeamListButton>
            </div>

        );
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className={"submit-container"}>
                    <button type="submit" disabled={!team1 || !team2}>Predict</button>
                </div>
                <div className={"set-list-btn-container"}>
                    <CreateButtonGroup setDisplayRegion={setDisplayRegion1} />
                    <CreateButtonGroup setDisplayRegion={setDisplayRegion2} />
                </div>

                <div className="dropdown">
                    <div className="dropdown">
                        <div className="content">
                            <Dropdown
                                buttonText={team1 || "Select Team 1"}
                                content={
                                    <>
                                        {displayRegion1.map(team => (
                                            <DropdownItem
                                                key={team}
                                                onClick={() => setTeam1(team)}
                                                disabled={team === team2}
                                            >
                                                {team}
                                            </DropdownItem>
                                        ))}
                                    </>
                                }
                            />
                        </div>

                        <div className="content">
                            <Dropdown
                                buttonText={team2 || "Select Team 2"}
                                content={
                                    <>
                                        {displayRegion2.map(team => (
                                            <DropdownItem
                                                key={team}
                                                onClick={() => setTeam2(team)}
                                                disabled={team === team1}
                                            >
                                                {team}
                                            </DropdownItem>
                                        ))}
                                    </>
                                }
                            />
                        </div>
                    </div>
                </div>

            </form>
            <div className="response-container">
                {response && (() => {
                    try {
                        const data = JSON.parse(response);
                        return (
                            <div>
                                {Object.entries(data).map(([key, value]) => (
                                    <div key={key} className="response-row">
                                        <span className="response-key">{key}:</span>
                                        <span className="response-value">{String(value)}</span>
                                    </div>
                                ))}
                            </div>
                        );
                    } catch {
                        return <div>{response}</div>;
                    }
                })()}
            </div>
        </div>
    );
}

export default App;