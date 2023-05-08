import React from "react";
import axios from "axios";
import {parseLabels} from "../../../util/utilfunctions.js";

export default function Label({note, fetchData}) {
    function handleLabelClick() {
        axios.put(`http://localhost:9000/api/v1/notes/put/nextlabel/${note.id}`)
            .then(() => fetchData())
    }

    return (
        <td onClick={handleLabelClick}>
            {parseLabels(note.label)}
        </td>
    )
}