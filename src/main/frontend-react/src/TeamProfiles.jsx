import {Link} from "react-router-dom";
import { ALL_TEAMS } from "./Predict";

import Select from 'react-select'


const TeamDropDownOptions = ALL_TEAMS.map(team => ({ value: team, label: team }));

function TeamProfiles(){
    return(
        <div>
            TeamProfiles
            <Select options={TeamDropDownOptions} />
        </div>
    );
}

export default TeamProfiles;