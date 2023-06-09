import React from "react";
import Button from "react-bootstrap/Button";
import axiosInstance from "../../util/apiClient.js";

export default function DeleteAllButton({fetchData}) {
    function handleDeleteAllButtonClick() {
        if (!confirm("Are you sure to delete all notes?")) {
            return
        }
        axiosInstance.delete("api/v1/notes")
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
