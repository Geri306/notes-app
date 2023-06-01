import '../styles/App.css'
import NotesTableAdmin from "../components/NotesTableAdmin.jsx";
import {useFetch} from "../hooks/useFetch.jsx";
import NavBar from "./NavBar.jsx";
const NOTES_URL = "/notes"; // TODO put it into a .env file

export default function Index({roles, loggedIn}) {
    const {data: notes, loading, error, fetchData} = useFetch(NOTES_URL);

    if (error) {
        return (
            <p>Error occurred: {error}</p>
        )
    }

    return (
        <>
            <NavBar loggedIn={loggedIn}/>
            <NotesTableAdmin loading={loading} notes={notes} fetchData={fetchData} roles={roles}/>
        </>
    )
}
