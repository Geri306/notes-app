import './App.css'
import NotesTable from "./components/table/NotesTable.jsx";
import {useFetch} from "./hooks/useFetch.js";

const NOTES_URL = "http://localhost:9000/api/v1/notes/get/all";

export default function App() {
    const {data: notes, loading, error, fetchData} = useFetch(NOTES_URL);

    if (error) {
        return (
            <p>Error occurred: {error}</p>
        )
    }

    return (
        <>
            {loading
                ? <small>Loading...</small>
                : <small>Loaded.</small>
            }
            <NotesTable notes={notes} fetchData={fetchData}/>
        </>
    )
}
