import { ALL_TEAMS } from "./Predict";

import Select from 'react-select'
import {useState} from "react";
import NavBar from "./components/NavBar/NavBar";


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


    return(
        <div>
            <NavBar></NavBar>
            <form className="team-profiles-form" onSubmit={handleSubmit}>
                <Select
                    options={TeamDropDownOptions}
                    value={selectedTeam}
                    onChange={setSelectedTeam}
                />
                <button type="submit">Fetch Profile</button>
            </form>

            <div className="response-container">
                {chosenProfile && (
                    <div className="team-profile">
                        <h2>Name: {chosenProfile.teamName}</h2>
                        <p><strong>Maps:</strong> {JSON.stringify(chosenProfile.teamMaps)}</p>

                    </div>
                )}
            </div>

        </div>
    );
}

export default TeamProfiles;