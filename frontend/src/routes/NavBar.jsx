import React from "react";
import '../styles/navbar.css'

export default function NavBar({loggedIn}) {
    return (
        loggedIn
            ? (<nav className="crumbs">
                <ul>
                    <li className="crumbs"><a href="logout">Logout</a></li>
                    <li className="crumbs">Logged in as: {loggedIn}</li>
                </ul>
            </nav>)
            : (<nav className="crumbs">
                <ul>
                    <li className="crumbs"><a href="login">Login</a></li>
                    <li className="crumbs"><a href="register">Register</a></li>
                </ul>
            </nav>)
    )
}