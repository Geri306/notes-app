import React from "react";
import {updateNoteInDb} from "../../../util/utilfunctions.js";

export default function Checkbox({note, fetchData}) {
     function handleCheck() {
        updateNoteInDb(note.id, {...note, done: !note.done})
            .then(() => fetchData())
    }

    return (
        <td>
            <input
                type={"checkbox"}
                checked={note.done}
                onChange={handleCheck}
            />
        </td>
    )
}