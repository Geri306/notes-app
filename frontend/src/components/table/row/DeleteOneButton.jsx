import React from "react";
import Button from 'react-bootstrap/Button';
import axiosInstance from "../../../util/apiClient.js";

export default function DeleteOneButton({note, fetchData}) {
    function handleDeleteRow() {
        if (!confirm("Are you sure to delete this note?")) {
            return
        }
        axiosInstance.delete(`api/v1/notes/${note.id}`)
            .then(() => fetchData())
            .catch(err => alert(err.message));
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