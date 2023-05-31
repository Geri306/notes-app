import React from "react";
import axios from "axios";
import {parseLabels} from "../../../util/utilFunctions.js";

export default function Label({note, fetchData, roles}) {
    function handleLabelClick() {
        if (!roles.includes("ADMIN")) {
            return
        }
        axios.put(`/notes/nextlabel/${note.id}`)
            .then(() => fetchData())
    }

    return (
        <td onClick={handleLabelClick}>
            {parseLabels(note.label)}
        </td>
    )
}