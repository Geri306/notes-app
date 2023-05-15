import './App.css'
import NotesTable from "./components/NotesTable.jsx";
import {useFetch} from "./hooks/useFetch.js";

// const NOTES_URL = "http://localhost:9000/api/v1/notes/get/all";
const NOTES_URL = "/notes/get/all"; // TODO put it into a .env file

export default function App() {
    const {data: notes, loading, error, fetchData} = useFetch(NOTES_URL);

    if (error) {
        return (
            <p>Error occurred: {error}</p>
        )
    }

    return (
        <>
            <NotesTable loading={loading} notes={notes} fetchData={fetchData}/>
        </>
    )
}
