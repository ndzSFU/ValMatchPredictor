import React, { useState } from 'react';
import Dropdown from './components/Dropdown/Dropdown';

function App() {
    const [team1, setTeam1] = useState('');
    const [team2, setTeam2] = useState('');
    const [response, setResponse] = useState('');

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
                <input
                    value={team1}
                    onChange={e => setTeam1(e.target.value)}
                    placeholder="Team 1"
                />
                <input
                    value={team2}
                    onChange={e => setTeam2(e.target.value)}
                    placeholder="Team 2"
                />
                <button type="submit">Predict</button>
            </form>
            <div>Response: {response}</div>

            <div className="Dropdown">
                <div className="Content">
                    <Dropdown
                    buttonText="Team 1" content={<p>place holder</p>}/>
                </div>
            </div>
        </div>
    );
}

export default App;