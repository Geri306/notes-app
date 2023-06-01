import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {Buffer} from 'buffer'
import {BASE_URL} from "../App.jsx";

export default function Login({setRoles, setLoggedIn}) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    // START


    // const handleLogin = async (e) => {
    //     try {
    //         // Send login request to backend
    //         const response = await axios.post(
    //             "http://localhost:8080/login",
    //             { username, password },
    //             { withCredentials: true }
    //         );
    //
    //         // Store credentials in local storage
    //         localStorage.setItem("credentials", JSON.stringify(response.data));
    //
    //         // Set logged in status to true
    //         setLoggedIn(true);
    //     } catch (error) {
    //         console.error(error);
    //     }
    // };


    const authString = `${email}:${password}`;
    const encodedAuth = Buffer.from(authString).toString('base64');
    /* const requestOptions = {
         headers: {
             Authorization: `Basic ${encodedAuth}`
         }
     }*/
    const config = {
        auth: {
            email: email,
            password: password
        }
    }
    const data = {
        email: email,
        password: password
    }

    const url = `${BASE_URL}/login`;

    async function handleLogin(e) {
        e.preventDefault();
        if (!email || !password) {
            return window.alert("email and password cannot be empty")
        }
        try {
            // const response = await axios.get(url, requestOptions);
            const response = await axios.post(url, data /*{ withCredentials: true }*/);
            console.log(response);
            if (response.status === 200) {
                const {data} = response
                const {email, roles, credentials} = data

                // Store credentials in local storage
                localStorage.setItem("credentials", credentials);

                // Set logged in status to true
                setLoggedIn(email);
                setRoles(roles);
                window.alert(`login successful with [${roles}] role(s), you'll be redirected..`);
                navigate("/")
            }
        } catch (err) {
            console.error(err)
            const {response: {data}} = err
            window.alert(data)
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
