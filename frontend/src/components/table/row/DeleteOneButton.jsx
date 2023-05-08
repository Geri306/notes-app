import React from "react";
import axios from "axios";
import {handleError} from "../../../util/utilFunctions.js";

export default function DeleteOneButton({note, fetchData}) {
    function handleDeleteRow() {
        axios.delete(`http://localhost:9000/api/v1/notes/delete/one/${note.id}`)
            .then(() => fetchData())
            .catch(err => handleError(`Error during deleting row ${note.id}. `, err))
    }

    return (
        <td>
            <button
                className={"delBtn"}
                onClick={handleDeleteRow}
            >Del</button>
        </td>
    )
}