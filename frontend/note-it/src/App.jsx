import './App.css'
import Table from "./components/table/Table.jsx";
import {useFetch} from "./hooks/useFetch.js";
import {useState} from "react";

const NOTES_URL = "http://localhost:9000/api/v1/notes/get/all";

export default function App() {
    const {data: notes, loading, error, fetchData} = useFetch(NOTES_URL);

    if (loading) {
        return (
            <p>Loading...</p>
        )
    }

    if (error) {
        return (
            <p>Error occurred: {error}</p>
        )
    }

    return <Table notes={notes} fetchData={fetchData} />
}
