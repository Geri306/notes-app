import React from "react";
import {handleError} from "../../util/utilFunctions.js";
import Button from "react-bootstrap/Button";
import axiosInstance from "../../util/apiClient.js";

export default function NewRowButton({fetchData}) {
    function handleNewButtonClick() {
        axiosInstance.post("api/v1/notes")
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
