import React from "react";
import Row from "./row/Row.jsx";
import NewRowButton from "./NewRowButton.jsx";
import DeleteAllButton from "./DeleteAllButton.jsx";
import Header from "./Header.jsx";

export default function Table({notes, fetchData}) {
    return (
        <>
            <h2>Notes</h2>
            <table>
                <thead>
                <Header/>
                </thead>
                <tbody>
                {notes && notes
                    .sort((a, b) => a.id - b.id)
                    .map(note =>
                        <Row key={note.id}
                             note={note}
                             fetchData={fetchData}
                        />
                    )}
                </tbody>
            </table>
            <NewRowButton fetchData={fetchData}/>
            <DeleteAllButton fetchData={fetchData}/>
        </>
    )
}