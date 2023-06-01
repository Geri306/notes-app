import Label from "./row/Label.jsx";
import Checkbox from "./row/Checkbox.jsx";
import Content from "./row/Content.jsx";
import DeleteOneButton from "./row/DeleteOneButton.jsx";

export default function TableRow({note, index, fetchData, roles}) {
    return (
        <tr key={note.id}>
            <td>{index + 1}</td>
            <Checkbox
                note={note}
                fetchData={fetchData}
                roles={roles}
            />
            <Content
                note={note}
                fetchData={fetchData}
                roles={roles}
            />
            <Label
                note={note}
                fetchData={fetchData}
                roles={roles}
            />
            <td>{note.modified}</td>
            {roles.includes("USER") &&
                <DeleteOneButton
                    note={note}
                    fetchData={fetchData}
                />
            }
        </tr>
    )
}