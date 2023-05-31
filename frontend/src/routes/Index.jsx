import '../styles/App.css'
import NotesTableAdmin from "../components/admin/NotesTableAdmin.jsx";
import NotesTableUser from "../components/user/NotesTableUser.jsx";
import {useFetch} from "../hooks/useFetch.js";
import NavBar from "./NavBar.jsx";
const NOTES_URL = "/notes"; // TODO put it into a .env file

export default function Index({isAdmin}) {
    const {data: notes, loading, error, fetchData} = useFetch(NOTES_URL);

    if (error) {
        return (
            <p>Error occurred: {error}</p>
        )
    }

    return (
        <>
            <NavBar/>
            {
                isAdmin
                ? <NotesTableAdmin loading={loading} notes={notes} fetchData={fetchData}/>
                : <NotesTableUser loading={loading} notes={notes}/>
            }
        </>
    )
}
