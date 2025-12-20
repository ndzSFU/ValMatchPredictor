import React, { useState } from 'react';
import Dropdown from './components/Dropdown/Dropdown';
import DropdownItem from "./components/DropdownItem/DropdownItem";
import './Predict.css';
import SetTeamListButton from "./components/SetTeamButton/SetTeamListButton";
import Select from 'react-select'
import NavBar from "./components/NavBar/NavBar";


const NA_teams = [
    "NRG",               "G2 Esports",        "Sentinels",         "100 Thieves",
    "Cloud9",            "Evil Geniuses",     "LEVIATÁN",          "KRÜ Esports",
    "MIBR",              "FURIA",             "LOUD",              "ENVY"
]

const EMEA_teams = [
    "FNATIC",            "Natus Vincere",              "Team Heretics",     "BBL Esports",
    "Team Liquid",       "FUT Esports",       "Team Vitality",     "Gentle Mates",
    "GIANTX",            "Karmine Corp",      "ULF Esports",       "BBL PCIFIC"
]

const APAC_teams = [
    "DetonatioN FocusMe","DRX",               "Gen.G",             "Global Esports",
    "Paper Rex",         "Rex Regum Qeon",    "T1",                "TALON",
    "Team Secret",       "ZETA DIVISION",     "SLT Seongnam",      "Nongshim RedForce",
]

const CN_teams = [
    "All Gamers",        "Bilibili Gaming",   "EDward Gaming",     "FunPlus Phoenix",
    "JDG Esports",       "Nova Esports",      "Titan Esports Club","Trace Esports",
    "TYLOO",             "Wolves Esports",    "Xi Lai Gaming",     "Dragon Ranger Gaming"
]

let all_teams = [...NA_teams, ...EMEA_teams, ...APAC_teams, ...CN_teams];

export const ALL_TEAMS = all_teams;

const CreateButtonGroup = ({setDisplayRegion}) => {

    const [pressedButtonId, setPressedButtonId] = useState(-1);
    return (
        <div>
            <SetTeamListButton id={1} title={"NA"} setTeamList={setDisplayRegion} teamList={NA_teams}
                               pressedButtonId={pressedButtonId} setPressedButtonId={setPressedButtonId}></SetTeamListButton>

            <SetTeamListButton id={2} title={"EMEA"} setTeamList={setDisplayRegion} teamList={EMEA_teams}
                               pressedButtonId={pressedButtonId} setPressedButtonId={setPressedButtonId}></SetTeamListButton>

            <SetTeamListButton id={3} title={"APAC"} setTeamList={setDisplayRegion} teamList={APAC_teams}
                               pressedButtonId={pressedButtonId} setPressedButtonId={setPressedButtonId}></SetTeamListButton>

            <SetTeamListButton id={4} title={"CHINA"} setTeamList={setDisplayRegion} teamList={CN_teams}
                               pressedButtonId={pressedButtonId} setPressedButtonId={setPressedButtonId}></SetTeamListButton>
        </div>

    );
}

function Predict() {
    const [team1, setTeam1] = useState(null);
    const [team2, setTeam2] = useState(null);
    const [response, setResponse] = useState('');
    const [displayRegion1, setDisplayRegion1] = useState(NA_teams);
    const [displayRegion2, setDisplayRegion2] = useState(NA_teams);

    const allTeamOptions = all_teams.map(team => ({
        value: team,
        label: team
    }));

    const team1_list = allTeamOptions.filter(team => team.value !== team2?.value);
    const team2_list = allTeamOptions.filter(team => team.value !== team1?.value);



    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log("Request body: ", { team1, team2 });
        try {
            const res = await fetch('http://localhost:8081/predict', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ team1: team1.value, team2: team2.value, bestOf: "3"}),
            });
            const data = await res.json();
            setResponse(JSON.stringify(data));
        } catch (error) {
            setResponse('Error: ' + error.message);
        }
    };

    const [open1, setOpen1] = React.useState(false);
    const [open2, setOpen2] = React.useState(false);

    const logos_url_names= ["t1LogoURL", "t2LogoURL"];

    return (
            <div>
                <NavBar></NavBar>
                <form onSubmit={handleSubmit}>


                    {/*<div className={"set-list-btn-container"}>*/}
                    {/*    <CreateButtonGroup setDisplayRegion={setDisplayRegion1} />*/}
                    {/*    <CreateButtonGroup setDisplayRegion={setDisplayRegion2} />*/}
                    {/*</div>*/}

                    <div className="dropdown">
                        <Select
                            options={team1_list}
                            value={team1}
                            onChange={setTeam1}
                            placeholder="Select Team 1"
                        />
                        <Select
                            options={team2_list}
                            value={team2}
                            onChange={setTeam2}
                            placeholder="Select Team 2"
                        />
                            {/*<div className="content">*/}
                            {/*    <Dropdown*/}
                            {/*        buttonText={team1 || "Select Team 1"}*/}
                            {/*        content={*/}
                            {/*            <>*/}
                            {/*                {displayRegion1.map(team => (*/}
                            {/*                    <DropdownItem*/}
                            {/*                        key={team}*/}
                            {/*                        onClick={() => setTeam1(team)}*/}
                            {/*                        disabled={team === team2}*/}
                            {/*                    >*/}
                            {/*                        {team}*/}
                            {/*                    </DropdownItem>*/}
                            {/*                ))}*/}
                            {/*            </>*/}
                            {/*        }*/}
                            {/*        open={open1}*/}
                            {/*        setOpen={setOpen1}*/}
                            {/*    />*/}
                            {/*</div>*/}

                            {/*<div className="content">*/}
                            {/*    <Dropdown*/}
                            {/*        buttonText={team2 || "Select Team 2"}*/}
                            {/*        content={*/}
                            {/*            <>*/}
                            {/*                {displayRegion2.map(team => (*/}
                            {/*                    <DropdownItem*/}
                            {/*                        key={team}*/}
                            {/*                        onClick={() => setTeam2(team)}*/}
                            {/*                        disabled={team === team1}*/}
                            {/*                    >*/}
                            {/*                        {team}*/}
                            {/*                    </DropdownItem>*/}
                            {/*                ))}*/}
                            {/*            </>*/}
                            {/*        }*/}
                            {/*        open={open2}*/}
                            {/*        setOpen={setOpen2}*/}
                            {/*    />*/}
                            {/*</div>*/}
                    </div>

                    <div className={"submit-container"}>
                        <button type="submit" disabled={!team1 || !team2}>Predict</button>
                    </div>

                </form>
                <div className="response-container">
                    {response && (() => {
                        try {
                            const data = JSON.parse(response);
                            return (
                                <div>
                                    {Object.entries(data).map(([key, value]) => {
                                        const isImage = logos_url_names.includes(key);

                                        return (
                                            <div key={key} className="response-row">
                                                <span className="response-key">{key}:</span>

                                                {isImage ? (
                                                    <img
                                                        src={value}
                                                        alt={key}
                                                        className="response-image"
                                                    />
                                                ) : (
                                                    <span className="response-value">{String(value)}</span>
                                                )}
                                            </div>
                                        );
                                    })}
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

export default Predict;