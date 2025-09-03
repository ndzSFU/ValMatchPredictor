import React, { useState } from 'react';
import Dropdown from './components/Dropdown/Dropdown';
import DropdownItem from "./components/DropdownItem/DropdownItem";
import './App.css';

function App() {
    const [team1, setTeam1] = useState('');
    const [team2, setTeam2] = useState('');
    const [response, setResponse] = useState('');
    const teams = [
        "NRG",
        "G2 Esports",
        "FNATIC",
        "Sentinels",
        "100 Thieves",
        "Cloud9",
        "Evil Geniuses",
        "LEVIATÁN",
        "KRÜ Esports",
        "MIBR"
    ];
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await fetch('http://localhost:8081/api/predict', {
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

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <div className={"submit-container"}>
                    <button type="submit" disabled={!team1 || !team2}>Predict</button>
                </div>
                <div className="dropdown">
                    <div className="dropdown">
                        <div className="content">
                            <Dropdown
                                buttonText={team1 || "Select Team 1"}
                                content={
                                    <>
                                        {teams.map(team => (
                                            <DropdownItem key={team} onClick={() => setTeam1(team)}>
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
                                        {teams.map(team => (
                                            <DropdownItem key={team} onClick={() => setTeam2(team)}>
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