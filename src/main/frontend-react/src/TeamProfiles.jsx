import { ALL_TEAMS } from "./Predict";

import Select from 'react-select'
import {useState} from "react";
import NavBar from "./components/NavBar/NavBar";
import { BarChartRangeExample, MapGraph } from "./components/MapGraph/MapGraph.jsx";
import "./TeamProfiles.css";

const TeamDropDownOptions = ALL_TEAMS.map(team => ({ value: team, label: team }));

async function fetchTeamProfile(teamName) {
    try{
        let url = "http://localhost:8081/teamProfile/" + teamName;
        const response = await fetch(url);
        const teamProfile = await response.json();
        console.log(teamProfile);
        return teamProfile;
    } catch(error){
        console.error("Error fetching team profile:", error);
    }

}

function TeamProfiles(){

    const [selectedTeam, setSelectedTeam] = useState(null);
    const [chosenProfile, setChosenProfile] = useState(null);

    const handleSubmit = async (event) => {
        event.preventDefault();
        const profileData = await fetchTeamProfile(selectedTeam.value)
        setChosenProfile(profileData);
    }

    const formattedMapWinRate = chosenProfile ? chosenProfile.teamMaps.map(map => (
        {name: map.name, WinRate: [0, map.roundWinRate] }))
        : [];

    const formattedMapPlayedQuantity = chosenProfile ? chosenProfile.teamMaps.map(map => (
        {name: map.name, AmountPlayed: [0, map.matchesPlayed]}
        )
    ) : [];

    const winRateFormatter = (value) => {
        if (Array.isArray(value)) {
            return [`${value[1]}%`, 'Win Rate'];
        }
        return value;
    }

    const amountPlayedFormatter = (value) => {
        if (Array.isArray(value)) {
            return [`${value[1]}`, 'Amount Played'];
        }
        return value;
    }

    return(
        <div>
            <NavBar></NavBar>
            <form className="team-profiles-form" onSubmit={handleSubmit}>
                <Select
                    options={TeamDropDownOptions}
                    value={selectedTeam}
                    onChange={setSelectedTeam}
                />
                <div className={"submit-container"}>
                    <button type="submit">Fetch Profile</button>
                </div>

            </form>
                {
                    chosenProfile && (
                        <div className="response-container">
                            <div className="team-profile">
                                <h2 className="map-graph-title">{chosenProfile.teamName}'s Win % Across Current Map Pool</h2>
                                <MapGraph MapData={formattedMapWinRate} xAxisLabel={"name"} yAxisLabel={"WinRate"} formatter={winRateFormatter}></MapGraph>

                                <h2 className="map-graph-title">{chosenProfile.teamName}'s Matches Played Per Map</h2>
                                <MapGraph MapData={formattedMapPlayedQuantity} xAxisLabel={"name"} yAxisLabel={"AmountPlayed"} formatter={amountPlayedFormatter}></MapGraph>
                            </div>

                        </div>
                )}


        </div>
    );
}

export default TeamProfiles;