import './PickBanMap.css';
export default function PickBanMap({mapName, teamName, type}) {

    const mapImgs = new Map();

    mapImgs.set("Abyss", "/Abyss.png");
    mapImgs.set("Ascent", "/Ascent.png");
    mapImgs.set("Bind", "/Bind.png");
    mapImgs.set("Lotus", "/Lotus.png");


    if(type === "DECIDER"){
        return(
            <div>
                PickBanMap Component
                {mapName} - {teamName} - {type}
                <div className="map-image-container">
                    <img src={mapImgs.get(mapName)} alt={mapName}></img>
                </div>

            </div>
        );
    }
    else{
        return(
            <div>
                PickBanMap Component
                {mapName} - {teamName} - {type}
                <div className="map-image-container banned-map-image-container">
                    <img src={mapImgs.get(mapName)} alt={mapName}></img>
                </div>

            </div>
        );
    }

}