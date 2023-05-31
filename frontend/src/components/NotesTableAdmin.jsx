import React from "react";
import TableRow from "./table/TableRow.jsx";
import NewRowButton from "./table/NewRowButton.jsx";
import DeleteAllButton from "./table/DeleteAllButton.jsx";
import TableHeader from "./table/TableHeader.jsx";
import {Spinner} from "react-bootstrap";

export default function NotesTableAdmin({loading, notes, fetchData, roles}) {
    return (
        <>
            <h3>N{loading
                ? <Spinner variant="dark" animation="border" size="sm"/>
                : "o"
            }tes</h3>
            <table>
                <thead>
                <TableHeader notes={notes}/>
                </thead>
                <tbody>
                {notes && notes
                    .sort((a, b) => a.id - b.id)
                    .map((note, i) =>
                        <TableRow key={note.id}
                                  index={i}
                                  note={note}
                                  fetchData={fetchData}
                                  roles={roles}
                        />
                    )}
                {notes &&
                    notes.length === 0 &&
                    (<tr className="noData">
                        <td className="noData">No notes</td>
                    </tr>)
                }
                </tbody>
            </table>
            {roles.includes("USER") && <NewRowButton fetchData={fetchData}/>}
            {roles.includes("ADMIN") && <DeleteAllButton fetchData={fetchData}/>}
        </>
    )
}
