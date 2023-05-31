import React from "react";

export default function Content({note}) {
    return (
        <td>
            <input
                style={{textDecoration: note.done && "line-through"}}
                value={note.content || ""}
                placeholder={"new note..."}
                disabled
            />
        </td>
    )
}