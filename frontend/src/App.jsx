import './styles/App.css'
import Login from "./routes/Login.jsx";
import UnknownPage from "./routes/UnknownPage.jsx";
import {Route, Routes} from "react-router-dom";
import Index from "./routes/Index.jsx";
import Registration from "./routes/Registration.jsx";
import {useEffect, useState} from "react";
import Logout from "./routes/Logout.jsx";
import {toHomePage} from "./util/utilFunctions.js";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export default function App() {
    const [roles, setRoles] = useState([])
    const [loggedIn, setLoggedIn] = useState(null)

    useEffect(() => {
        const storedCredentials = localStorage.getItem("credentials")
        if (storedCredentials) {
            setLoggedIn(storedCredentials)
        }
    }, []);

    function handleLogout() {
        localStorage.removeItem("credentials")
        setLoggedIn(null)
        toHomePage()
    }

    return (
        <Routes>
            <Route path="/" element={<Index roles={roles} loggedIn={loggedIn}/>} />
            <Route path="register" element={<Registration roles={roles}/>}/>
            <Route path="login" element={<Login setRoles={setRoles} setLoggedIn={setLoggedIn}/>}/>
            <Route path="logout" element={<Logout handleLogout={handleLogout}/>}/>
            <Route path="*" element={<UnknownPage/>}/>
        </Routes>
    )
}
