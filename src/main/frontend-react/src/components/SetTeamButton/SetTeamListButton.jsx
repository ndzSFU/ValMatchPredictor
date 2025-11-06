import React from 'react';
import './SetDropdownList.css';

const SetTeamListButton = ({title, teamList, setTeamList}) => {
    return (
        <button className={"set-dropdown-list-btn"} onClick={() => {setTeamList(teamList)}}> {title}</button>
    );
}

export default SetTeamListButton;