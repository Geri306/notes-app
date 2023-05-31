import './styles/App.css'
import Login from "./routes/Login.jsx";
import UnknownPage from "./routes/UnknownPage.jsx";
import {Route, Routes} from "react-router-dom";
import Index from "./routes/Index.jsx";
import Registration from "./routes/Registration.jsx";
import {useState} from "react";

export const BASE_URL = import.meta.env.VITE_BASE_URL;

export default function App() {
    const [isAdmin, setIsAdmin] = useState(false);

    return (
        <Routes>
            <Route path="/" element={<Index isAdmin={isAdmin}/>} />
            <Route path="register" element={<Registration/>}/>
            <Route path="login" element={<Login setIsAdmin={setIsAdmin}/>}/>
            <Route path="*" element={<UnknownPage/>}/>
        </Routes>
    )
}
