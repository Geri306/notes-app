import React from "react";
import {updateNoteInDb} from "../../../util/utilFunctions.js";

export default function Checkbox({note, fetchData, roles}) {
    function handleCheck() {
        updateNoteInDb(note.id, {...note, done: !note.done})
            .then(() => fetchData())
    }

    return (
        <td>
            <input
                type="checkbox"
                className="clickable"
                checked={note.done}
                onChange={handleCheck}
                disabled={!roles.includes("ADMIN")}
            />
        </td>
    )
}
