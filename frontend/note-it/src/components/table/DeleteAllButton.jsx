import React from "react";
import axios from "axios";

export default function DeleteAllButton({fetchData}) {
    function handleDeleteAllButtonClick() {
        axios.delete("http://localhost:9000/api/v1/notes/delete/all")
            .then(() => fetchData())
    }

    return (
        <button className={"delBtn"}
            onClick={handleDeleteAllButtonClick}
        >Delete all</button>
    )
}
