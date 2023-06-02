import './styles/App.css'
import Login from "./routes/Login.jsx";
import UnknownPage from "./routes/UnknownPage.jsx";
import {Route, Routes} from "react-router-dom";
import Index from "./routes/Index.jsx";
import Registration from "./routes/Registration.jsx";
import {useEffect, useState} from "react";
import Logout from "./routes/Logout.jsx";
import {toHomePage} from "./util/utilFunctions.js";
import {Buffer} from "buffer";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export default function App() {
    const [roles, setRoles] = useState([])
    const [loggedIn, setLoggedIn] = useState(null)

    useEffect(() => {
        const storedCredentials = localStorage.getItem("credentials")
        if (storedCredentials) {
            const buffer = Buffer.from(storedCredentials, 'base64');
            const email = buffer.toString('utf-8').split(":")[0]
            setLoggedIn(email)
            const roles = localStorage.getItem("roles").split(",")
            setRoles(roles)
        }
        if (!storedCredentials) {
            localStorage.removeItem("roles")
        }
    }, []);

    function handleLogout() {
        localStorage.removeItem("credentials")
        localStorage.removeItem("roles")
        setLoggedIn(null)
        toHomePage()
    }

    return (
        <Routes>
            <Route path="/" element={<Index roles={roles} setRoles={setRoles} loggedIn={loggedIn}/>} />
            <Route path="register" element={<Registration roles={roles}/>}/>
            <Route path="login" element={<Login setRoles={setRoles} setLoggedIn={setLoggedIn}/>}/>
            <Route path="logout" element={<Logout handleLogout={handleLogout}/>}/>
            <Route path="*" element={<UnknownPage/>}/>
        </Routes>
    )
}
