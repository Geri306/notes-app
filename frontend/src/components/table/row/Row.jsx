import React from "react";
import Label from "./Label.jsx";
import Checkbox from "./Checkbox.jsx";
import NoteContent from "./NoteContent.jsx";
import DeleteOneButton from "./DeleteOneButton.jsx";

export default function Row({note, fetchData}) {
    return (
        <tr key={note.id}>
            <td>{note.id}</td>
            <Checkbox
                note={note}
                fetchData={fetchData}
            />
            <NoteContent
                note={note}
                fetchData={fetchData}
            />
            <Label
                note={note}
                fetchData={fetchData}
            />
            <td>{note.modified}</td>
            <DeleteOneButton
                note={note}
                fetchData={fetchData}
            />
        </tr>
    )
}