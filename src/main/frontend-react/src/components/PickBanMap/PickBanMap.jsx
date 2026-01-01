import './PickBanMap.css';
export default function PickBanMap({mapName, teamName, type}) {

    const mapImgs = new Map();

    mapImgs.set("Abyss", "/Abyss.png");
    mapImgs.set("Ascent", "/Ascent.png");
    mapImgs.set("Bind", "/Bind.png");
    mapImgs.set("Lotus", "/Lotus.png");
    mapImgs.set("Haven", "/Haven.png");
    mapImgs.set("Corrode", "/Corrode.png");
    mapImgs.set("Sunset", "/Sunset.png");


    if(type === "DECIDER"){
        return(
            <div className="pick-ban-map-container">
                <h4 className="map-name">{mapName.toUpperCase()}</h4>
                <p className="map-subtext"> Decider</p>
                <div className="map-image-container">
                    <img src={mapImgs.get(mapName)} alt={mapName}></img>
                </div>

            </div>
        );
    }
    else if(type == "T1PICK" || type == "T2PICK"){
        return(
            <div className="pick-ban-map-container">
                <h4 className="map-name">{mapName.toUpperCase()}</h4>
                <p className="map-subtext">{teamName} - Pick</p>
                <div className="map-image-container">
                    <img src={mapImgs.get(mapName)} alt={mapName}></img>
                </div>

            </div>
        );

    }
    else{
        return(
            <div className="pick-ban-map-container">
                <h4 className="map-name">{mapName.toUpperCase()}</h4>
                <p className="map-subtext">{teamName} - Ban</p>

                <div className="map-image-container banned-map-image-container">
                    <img src={mapImgs.get(mapName)} alt={mapName}></img>
                </div>

            </div>
        );
    }

}