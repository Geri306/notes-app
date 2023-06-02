import React from "react";
import {parseLabels} from "../../../util/utilFunctions.js";
import axiosInstance from "../../../util/apiClient.js";

export default function Label({note, fetchData, roles}) {
    function handleLabelClick() {
        if (!roles.includes("ADMIN")) {
            return
        }
        axiosInstance.put(`api/v1/notes/nextlabel/${note.id}`)
            .then(() => fetchData())
    }

    return (
        <td className="clickable" onClick={handleLabelClick}>
            {parseLabels(note.label)}
        </td>
    )
}