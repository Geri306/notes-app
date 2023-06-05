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

    async function handleLogin(e) {
        e.preventDefault()
        if (!email || !password) {
            return alert("email and password cannot be empty")
        }
        try {
            const {data} = await axios({
                method: 'post',
                url: 'http://localhost:9000/login',
                headers: {'Authorization': 'Basic ' + encodedAuth}
            })
            localStorage.setItem("credentials", encodedAuth)
            localStorage.setItem("roles", data)
            setLoggedIn(email)
            setRoles(data)
            alert(`login successful with ${data} role(s), you'll be redirected..`)
            navigate("/")
        } catch (err) {
            console.error(err)
            alert(err)
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
