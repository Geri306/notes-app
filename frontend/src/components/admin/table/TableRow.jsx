import Label from "./row/Label.jsx";
import Checkbox from "./row/Checkbox.jsx";
import Content from "./row/Content.jsx";
import DeleteOneButton from "./row/DeleteOneButton.jsx";

export default function TableRow({note, index, fetchData}) {
    return (
        <tr key={note.id}>
            <td>{index + 1}</td>
            <Checkbox
                note={note}
                fetchData={fetchData}
            />
            <Content
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