import React from "react";
import axios from "axios";
import {parseLabels} from "../../../util/utilFunctions.js";

export default function Label({note, fetchData}) {
    function handleLabelClick() {
        axios.put(`/notes/put/nextlabel/${note.id}`)
            .then(() => fetchData())
    }

    return (
        <td onClick={handleLabelClick}>
            {parseLabels(note.label)}
        </td>
    )
}