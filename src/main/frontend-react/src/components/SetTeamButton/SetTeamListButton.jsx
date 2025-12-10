import './SetDropdownList.css';

const SetTeamListButton = ({id, title, teamList, setTeamList, pressedButtonId, setPressedButtonId}) => {
    return (
        <button type="button"
                onClick={() => {setTeamList(teamList); setPressedButtonId(id);}}
                className={(id === pressedButtonId) ? "pressed-set-dropdown-list-btn" : "set-dropdown-list-btn"}
                >
                {title}
        </button>
    );
}

export default SetTeamListButton;