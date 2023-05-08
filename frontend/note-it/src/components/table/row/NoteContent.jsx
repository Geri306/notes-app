import React from "react";
import {updateNoteInDb} from "../../../util/utilfunctions.js";

export default function NoteContent({note, fetchData}) {
    function handleNoteContentChange(e) {
        updateNoteInDb(note.id, {...note, content: e.target.value})
            .then(() => fetchData())
    }

    return (
        <td>
            <input
                value={note.content || ""}
                disabled={note.done}
                onChange={handleNoteContentChange}
            />
        </td>
    )
}