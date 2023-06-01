import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {BASE_URL} from "../App.jsx";
import {Buffer} from "buffer";

export default function Registration({roles}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [isAdmin, setIsAdmin] = useState(false)
    const navigate = useNavigate();

    const authString = `${username}:${password}`;
    const encodedAuth = Buffer.from(authString).toString('base64');

    const url = `${BASE_URL}/register${isAdmin ? "/admin" : ""}`;
    console.log(url);
    const data = {
        "encodedAuth": encodedAuth,
        "asAdmin": isAdmin
    };

    async function handleRegister(e) {
        e.preventDefault();
        if (!username || !password) {
            return window.alert("username and password cannot be empty")
        }
        try {
            await axios.post(url, data)
            window.alert("registration successful, you'll be redirected..")
            navigate("/login");
        } catch (err) {
            console.log(err)
            const {response: {data: {message}}} = err
            window.alert(message)
        }
    }

    function handleCancel() {
        navigate("/")
    }

    return (
        <form id="register">
            <div>
                <p>Registration</p>
            </div>
            <div>
                <label>Email :
                    <input type="email"
                           autoComplete="email"
                           name="email"
                           onChange={(e) => setUsername(e.target.value)}
                    />
                </label>
            </div>
            <div>
                <label> Password:
                    <input type="password"
                           autoComplete="new-password"
                           name="password"
                           onChange={(e) => setPassword(e.target.value)}
                    />
                </label>
            </div>
            {/*{roles.includes("ADMIN") && }*/}
                <label> As admin:
                    <input type="checkbox"
                           name="isAdmin"
                           onChange={() => setIsAdmin(!isAdmin)}
                    />
                </label>

            <div>

            </div>
            <div>
                <input type="submit"
                       value="Registration!"
                       onClick={handleRegister}
                />
            </div>
            <div>
                <input type="button"
                       value="Cancel"
                       onClick={handleCancel}
                />
            </div>
        </form>
    )
}


