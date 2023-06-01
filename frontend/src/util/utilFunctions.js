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
        await axios.put(`/notes/${wantedId}`, updatedNote);
    } catch (err) {
        handleError("Error during update: " + err)
    }

}

export function handleError(message, error) {
    console.error(message + error)
    throw new Error(message + error)
}

export function toHomePage() {
    setTimeout(() => {
        window.location = ("/");
    }, 1_000);
}




