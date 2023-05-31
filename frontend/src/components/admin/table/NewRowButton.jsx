import React from "react";
import axios from "axios";
import {handleError} from "../../../util/utilFunctions.js";
import Button from "react-bootstrap/Button";

export default function NewRowButton({fetchData}) {
    function handleNewButtonClick() {
        axios.post("/notes")
            .then(() => fetchData())
            .catch(err => handleError("Error during creating new note. " + err))
    }

    return (
        <Button
            size="sm"
            style={{margin: ".5rem"}}
            onClick={handleNewButtonClick}
        >New</Button>
    )
}
