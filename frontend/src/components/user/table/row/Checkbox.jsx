import React from "react";

export default function Checkbox({note}) {
    return (
        <td>
            <input
                type={"checkbox"}
                checked={note.done}
                disabled
            />
        </td>
    )
}
