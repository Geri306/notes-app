import React from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";

export default function DeleteAllButton({fetchData}) {
    function handleDeleteAllButtonClick() {
        axios.delete("/notes/delete/all")
            .then(() => fetchData())
    }

    return (
        <Button
            size="sm"
            variant="outline-danger"
            onClick={handleDeleteAllButtonClick}
        >Delete all</Button>
    )
}
