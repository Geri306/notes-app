import React from "react";
import axios from "axios";
import {handleError} from "../../../util/utilFunctions.js";
import Button from 'react-bootstrap/Button';

export default function DeleteOneButton({note, fetchData}) {
    function handleDeleteRow() {
        axios.delete(`/notes/delete/one/${note.id}`)
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