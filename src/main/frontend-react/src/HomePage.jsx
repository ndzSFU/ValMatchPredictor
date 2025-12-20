import { NavLink } from "react-router";
import NavBar from "./components/NavBar/NavBar";
import colouredLogo from "./images/coloured-logo.png";
import "./HomePage.css";

function HomePage() {
    return(
        <div>
            <NavBar></NavBar>
            <img
                src={colouredLogo}
                className="home-page-logo"
            />
        </div>
    );
}

export default HomePage;