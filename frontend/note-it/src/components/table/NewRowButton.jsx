import React from "react";
import axios from "axios";
import {handleError} from "../../util/utilFunctions.js";

export default function NewRowButton({fetchData}) {
    function handleNewButtonClick() {
        axios.post("http://localhost:9000/api/v1/notes/new")
            .then(() => fetchData())
            .catch(err => handleError("Error during creating new note. " + err))
    }

    return (
        <button
            onClick={handleNewButtonClick}
        >New</button>
    )
}
