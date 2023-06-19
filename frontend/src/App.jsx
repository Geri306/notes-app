import './styles/app.css'
import Login from "./routes/Login.jsx";
import UnknownPage from "./routes/UnknownPage.jsx";
import {Route, Routes, useNavigate} from "react-router-dom";
import Index from "./routes/Index.jsx";
import Registration from "./routes/Registration.jsx";
import {useEffect, useState} from "react";
import Logout from "./routes/Logout.jsx";
import axiosInstance from "./util/apiClient.js";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export default function App() {
    const [roles, setRoles] = useState([])
    const [loggedIn, setLoggedIn] = useState(null)
    const navigate = useNavigate();

    useEffect(() => {
        if (!localStorage.getItem("token")) {
            setLoggedIn(null)
            setRoles([])
            return
        }
        try {
            (async () => {
                const {data: {roles, email}} = await axiosInstance.get("http://localhost:9000/latest_userdetails");
                setRoles(roles)
                setLoggedIn(email)
            })()
        } catch (e) {
            console.error(e)
            localStorage.removeItem("token")
        }
    }, []);

    function handleLogout() {
        localStorage.removeItem("token")
        setLoggedIn(null)
        setRoles([])
        navigate("/")
    }

    return (
        <Routes>
            <Route path="/" element={<Index roles={roles} setRoles={setRoles} loggedIn={loggedIn}/>}/>
            <Route path="register" element={<Registration roles={roles}/>}/>
            <Route path="login" element={<Login setRoles={setRoles} setLoggedIn={setLoggedIn}/>}/>
            <Route path="logout" element={<Logout handleLogout={handleLogout}/>}/>
            <Route path="*" element={<UnknownPage/>}/>
        </Routes>
    )
}
