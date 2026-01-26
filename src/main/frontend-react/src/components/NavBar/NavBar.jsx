import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
// import NavDropdown from 'react-bootstrap/NavDropdown';
import { NavLink } from "react-router-dom";
import './NavBar.css';


export default function NavBar() {
    return (
        <Navbar expand="lg" className="bg-dark" variant="dark">
            <Container className="nav-bar-container d-flex align-items-center">
                <Navbar.Brand href="/" className="d-flex align-items-center">
                    <div className="logo-wrapper">
                        <img
                            src={"./logo-no-text.png"}
                            alt="Valorant Logo"
                            className="navbar-logo"
                        />
                    </div>

                </Navbar.Brand>

                {/* Add ms-auto to push links to the right of brand */}
                <Nav className="d-flex align-items-center ml-3">
                    <NavLink to="/predict" className="nav-link">
                        Predict
                    </NavLink>
                    <NavLink to="/team" className="nav-link">
                        Team Stats
                    </NavLink>
                    <NavLink to="/about" className="nav-link">
                        About API
                    </NavLink>
                </Nav>
            </Container>
        </Navbar>
    );
}
