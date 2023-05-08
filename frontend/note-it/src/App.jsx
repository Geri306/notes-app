import './App.css'
import Table from "./components/table/Table.jsx";
import {useFetch} from "./hooks/useFetch.js";
import {useState} from "react";

export default function App() {
    const [url, setUrl] = useState("http://localhost:9000/api/v1/notes/get/all");
    const {data: notes, loading, error, fetchData} = useFetch(url);

    return loading
        ? <p>Loading...</p>
        : error
            ? <p>Error occurred: {error}</p>
            : <Table notes={notes} fetchData={fetchData} />
}
