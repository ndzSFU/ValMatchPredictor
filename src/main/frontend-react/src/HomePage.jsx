import { NavLink } from "react-router";
import NavBar from "./components/NavBar/NavBar";
import "./HomePage.css";

function HomePage() {
    return (
        <div className="home-page">
            <NavBar />
            <div className="landing-content">
                <img src={"transparent-logo.png"} alt="App Logo" className="home-page-logo" />
                <h1 className="landing-title">Welcome to The Valorant Match Predictor!</h1>
                <p className="landing-desc">
                    Get theoretical match predictions and Team stats, via the UI on the site or you can gather the data via the API, more info here.
                </p>
                <div className="landing-actions">
                    <NavLink to="/predict" className="landing-btn">Predict a Match</NavLink>
                    <NavLink to="/team-profiles" className="landing-btn">View Team Profiles</NavLink>
                </div>
            </div>
        </div>
    );
}

export default HomePage;