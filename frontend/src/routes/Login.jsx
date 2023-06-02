import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {Buffer} from 'buffer'
import {BASE_URL} from "../App.jsx";

export default function Login({setRoles, setLoggedIn}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const authString = `${email}:${password}`;
    const encodedAuth = Buffer.from(authString).toString('base64');
    const requestOptions = {
        headers: {
            Authorization: `Basic ${encodedAuth}`
        }
    }

    const URL = `${BASE_URL}/login`;

    async function handleLogin(e) {
        e.preventDefault();
        if (!email || !password) {
            return alert("email and password cannot be empty")
        }
        try {
            const {data: {email, roles, credentials}} = await axios.get(URL, requestOptions);
            localStorage.setItem("credentials", credentials);
            localStorage.setItem("roles", roles)
            setLoggedIn(email);
            setRoles(roles)
            alert(`login successful with [${roles}] role(s), you'll be redirected..`);
            navigate("/")
        } catch (err) {
            console.error(err)
            const {response: {data}} = err
            alert(data)
        }
    }

    function handleCancel() {
        navigate("/")
    }

    return (
        <>
            <form id="login">
                <div>
                    <p>Login</p>
                </div>
                <div>
                    <label>
                        Email :
                        <input
                            type="email"
                            autoComplete="email"
                            name="email"
                            onChange={(e) => setEmail(e.target.value)}
                        />
                    </label>
                </div>
                <div>
                    <label>
                        Password:
                        <input
                            type="password"
                            autoComplete="current-password"
                            name="password"
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </label>
                </div>
                <div>
                    <input
                        type="submit"
                        value="Log in!"
                        onClick={handleLogin}
                    />
                </div>
                <div>
                    <input
                        type="button"
                        value="Cancel"
                        onClick={handleCancel}
                    />
                </div>
            </form>
        </>
    )
}
