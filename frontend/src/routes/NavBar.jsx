import React from "react";
import '../styles/navbar.css'
export default function NavBar() {
    return (
        <nav className="crumbs">
            <ul>
                <li className="crumbs"><a href="login">Login</a></li>
                <li className="crumbs"><a href="register">Register</a></li>
            </ul>
        </nav>
    )
}