import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {Buffer} from 'buffer'

export default function Login({setRoles, setLoggedIn}) {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()

    const authString = `${email}:${password}`
    const encodedAuth = Buffer.from(authString).toString('base64')

    const requestOptions = {
        method: 'post',
        url: 'http://localhost:9000/login',
        headers: {'Authorization': 'Basic ' + encodedAuth}
    };

    async function handleLogin(e) {
        e.preventDefault()
        if (!email || !password) {
            alert("email and password cannot be empty")
            return
        }
        try {
            const {data: {roles, token}} = await axios(requestOptions)
            localStorage.setItem("token", token)
            setLoggedIn(email)
            setRoles(roles)
            alert(`login successful with ${roles} role(s), you'll be redirected..`)
            navigate("/")
        } catch (e) {
            console.error(e)
            alert(e)
        }
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
                        onClick={() => navigate("/")}
                    />
                </div>
            </form>
        </>
    )
}
