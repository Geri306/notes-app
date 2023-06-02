import React from "react";
import axios from "axios";
import Button from 'react-bootstrap/Button';

export default function DeleteOneButton({note, fetchData}) {

    const requestOptions = {
        headers: {
            Authorization: `Basic ${localStorage.getItem("credentials")}`
        }
    }

    function handleDeleteRow() {
        if (!window.confirm("Are you sure to delete this notes?")) {
            return
        }
        axios.delete(`/notes/${note.id}`, requestOptions)
            .then(() => fetchData())
            .catch(err => alert(`${err.response.status} - ${err.response.statusText} `));
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