import '../styles/navbar.css'
import {NavLink} from "react-router-dom";

export default function NavBar({loggedIn}) {
    return (
        loggedIn
            ? (<nav className="crumbs">
                <ul>
                    <li className="crumbs">
                        <NavLink to="logout">Logout</NavLink>
                    </li>
                    <li className="crumbs">Logged in as: {loggedIn}</li>
                </ul>
            </nav>)
            : (<nav className="crumbs">
                <ul>
                    <li className="crumbs">
                        <NavLink to="/login">Login
                        </NavLink></li>
                    <li className="crumbs">
                        <NavLink to="register">Register</NavLink>
                    </li>
                </ul>
            </nav>)
    )
}