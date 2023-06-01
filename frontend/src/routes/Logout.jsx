import {useEffect} from "react";

export default function Logout({handleLogout}) {
    useEffect(() => {
        handleLogout();
    },[])

    return (
        <p>Logging out..</p>
    )
}