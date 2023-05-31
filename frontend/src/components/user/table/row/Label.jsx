import React from "react";
import {parseLabels} from "../../../../util/utilFunctions.js";

export default function Label({note}) {
    return (
        <td>
            {parseLabels(note.label)}
        </td>
    )
}