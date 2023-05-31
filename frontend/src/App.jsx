import './styles/App.css'
import Login from "./routes/Login.jsx";
import UnknownPage from "./routes/UnknownPage.jsx";
import {Route, Routes} from "react-router-dom";
import Index from "./routes/Index.jsx";
import Registration from "./routes/Registration.jsx";
import {useState} from "react";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export default function App() {
    const [roles, setRoles] = useState([]);

    console.log(roles);
    return (
        <Routes>
            <Route path="/" element={<Index roles={roles}/>} />
            <Route path="register" element={<Registration roles={roles}/>}/>
            <Route path="login" element={<Login setRoles={setRoles}/>}/>
            <Route path="*" element={<UnknownPage/>}/>
        </Routes>
    )
}
