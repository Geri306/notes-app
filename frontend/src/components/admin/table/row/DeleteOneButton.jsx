import React from "react";
import axios from "axios";
import {handleError} from "../../../../util/utilFunctions.js";
import Button from 'react-bootstrap/Button';

export default function DeleteOneButton({note, fetchData}) {
    function handleDeleteRow() {
        if (!window.confirm("Are you sure to delete this notes?")) {
            return
        }
        axios.delete(`/notes/${note.id}`)
            .then(() => fetchData())
            .catch(err => handleError(`Error during deleting row ${note.id}. `, err))
    }

    return (
        <td>
            <Button
                size="sm"
                style={{margin: ".5rem"}}
                variant="outline-danger"
                onClick={handleDeleteRow}
            >Del</Button>
        </td>
    )
}