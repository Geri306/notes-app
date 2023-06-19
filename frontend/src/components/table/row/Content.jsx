import React, {useEffect, useRef, useState} from "react";
import {updateNoteInDb} from "../../../util/utilFunctions.js";

export default function Content({note, fetchData, roles}) {
    const [cursor, setCursor] = useState();
    const ref = useRef(null);

    function handleNoteContentChange(e) {
        setCursor(e.target.selectionStart);
        updateNoteInDb(note.id, {...note, content: e.target.value})
            .then(() => fetchData())
    }

    useEffect(() => {
        const input = ref.current;
        input.setSelectionRange(cursor, cursor);
    }, [note.content]);

    return (
        <td>
            <input
                ref={ref}
                style={{ textDecoration: note.done && "line-through"}}
                value={note.content || ""}
                placeholder={"new note..."}
                disabled={note.done || !roles.includes("USER")}
                onChange={handleNoteContentChange}
            />
        </td>
    )
}