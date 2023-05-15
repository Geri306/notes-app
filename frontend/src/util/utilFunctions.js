import axios from 'axios';

export function parseLabels(label) {
    return label === "GREEN"
        ? "🟢"
        : label === "YELLOW"
            ? "🟡"
            : "🔴";
}

export async function updateNoteInDb(wantedId, updatedNote) {
    try {
        await axios.put(`/notes/put/${wantedId}`, updatedNote);
    } catch (err) {
        handleError("Error during update: " + err)
    }

}

export function handleError(message, error) {
    console.error(message + error)
    throw new Error(message + error)
}

