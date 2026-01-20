import BarLoader from "react-spinners/BarLoader";
import React from "react";

export default function LoadingInfo(){
    return(
        <div>
            <p className={"loading-text"}>The backend will take a minute or two to spin up after prolonged inactivity, once it's up your response will appear and subsequent
                responses should arrive at in just a few seconds!
            </p>

            <div className={"loader-bar"}>
                <BarLoader color={"#f34602"} width={300} height={7} />
            </div>

        </div>
    );
}
