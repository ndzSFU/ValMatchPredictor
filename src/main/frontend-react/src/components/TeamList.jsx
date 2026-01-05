
export default function TeamList({Title, Team_List}) {
    return(
        <div className="team-list">
            <div className="team-list-header">
                <h3>{Title}</h3>
            </div>
            {Team_List.map(team => (<li key={team}>{team}</li>))}
        </div>
    );
}