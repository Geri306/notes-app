import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {Buffer} from 'buffer'
import {BASE_URL} from "../App.jsx";

export default function Login({setIsAdmin}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const authString = `${username}:${password}`;
    const encodedAuth = Buffer.from(authString).toString('base64');
    const requestOptions = {
        headers: {
            Authorization: `Basic ${encodedAuth}`
        }
    }

    const url = `${BASE_URL}/login`;

    async function handleLogin(e) {
        e.preventDefault();
        try {
            const response = await axios.get(url, requestOptions);
            if (response.status === 200) {
                const {data:{roles}} = response;
                if (roles.includes("ADMIN")) {
                    setIsAdmin(true);
                } else {
                    setIsAdmin(false);
                }
                window.alert(`login successful with [${roles}] role(s), you'll be redirected..`);
                navigate("/")
            }
        } catch (err) {
            console.error(err)
            const {response: {data: {message}}} = err
            window.alert(message)
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
                            onChange={(e) => setUsername(e.target.value)}
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
