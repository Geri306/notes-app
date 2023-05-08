import React from "react";
import {updateNoteInDb} from "../../../util/utilFunctions.js";

export default function NoteContent({note, fetchData}) {
    function handleNoteContentChange(e) {
        updateNoteInDb(note.id, {...note, content: e.target.value})
            .then(() => fetchData())
    }

    return (
        <td>
            <input
                style={{ textDecoration: note.done && "line-through"}}
                value={note.content || ""}
                placeholder={"new note..."}
                disabled={note.done}
                onChange={handleNoteContentChange}
            />
        </td>
    )
}