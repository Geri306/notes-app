import React from "react";
import Label from "./row/Label.jsx";
import Checkbox from "./row/Checkbox.jsx";
import Content from "./row/Content.jsx";

export default function TableRow({note, index}) {
    return (
        <tr key={note.id}>
            <td>{index + 1}</td>
            <Checkbox note={note}/>
            <Content note={note}/>
            <Label note={note}/>
            <td>{note.modified}</td>
        </tr>
    )
}